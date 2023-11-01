package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import board.domain.BoardVO;
import board.service.BoardService;
import board.service.BoardServiceImpl;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import paging.domain.PagingVO;


@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// log 객체 생성
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	// service 객체 생성
	private BoardService bsv;
	// requestDisfatcher 객체 생성
	private RequestDispatcher rdp;
	// 목적지 경로 변수
	private String destPage = "";
	// 결과 확인 변수
	private int isOk;
	// 파일 경로 저장 변수
	private String savePath;
	

    public BoardController() {
    	// 서비스 객체 연결
        bsv = new BoardServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// encoding 설정, contentType 설정
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청 경로 파악
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info("path >> "+path);
		
		switch (path) {
		case "register":
			destPage = "/board/register.jsp";
			break;
		case "writing":
			log.info("글쓰기 페이지로 이동");
			try {
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				log.info("파일 저장 위치 >> "+ savePath);
				
				// 파일 객체를 생성하기 위한 객체(파일 정보를 설정)
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir); // 저장할 위치 set(file 객체로 지정)
				fileItemFactory.setSizeThreshold(3*1024*1024); // 임시 메모리 용령 설정
				
				BoardVO bvo = new BoardVO();
				// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				for(FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "writer":
						bvo.setWriter(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "img_file":
						// 이미지 저장 처리가 필요
						// 이미지 파일이 없는 경우도 처리
						if(item.getSize() > 0) { // 데이터 크기가 있다면 파일이 있는걸로 처리
							// 경로를 포함해서 들어오는 케이스는 이름만 잘라서 저장
							String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1); // 파일 이름만 분리
							
							// 시스템의 현재 시간_파일이름.jpg
							fileName = System.currentTimeMillis() + "_" + fileName;
							
							// 파일 객체 생성 : D:~/fileUpload/현재시간_파일네임.jpg
							File uploadFilePath = new File(fileDir + File.separator + fileName);
							log.info("파일경로 + 이름 >> " + uploadFilePath);
							
							// 저장
							try {
								// 자바 객체를 디스크에 쓰기
								item.write(uploadFilePath);
								bvo.setImg_file(fileName);
								
								// 썸네일 작업 : 트래픽 과다 사용 방지
								Thumbnails.of(uploadFilePath).size(60, 60).toFile(new File(fileDir + File.separator + "_th_" + fileName));
								
							} catch (Exception e) {
								log.info("file writer on disk error");
								e.printStackTrace();
							}
						}
						break;
					}
				}
				// DB에 저장
				isOk = bsv.insert(bvo);
				log.info("insert >> "+ (isOk > 0? "Ok":"Fail"));
				destPage = "/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "list" :
			try {
				// 화면에서 파라미터 받기
				log.info("게시글 목록");
				PagingVO pgvo = new PagingVO();
				
				if(request.getParameter("pageNo") != null ) {
					int pageNo = Integer.parseInt(request.getParameter("pageNo")); // 선택한 페이지 번호
					int qty = 10; // 한 페이지당 보여질 게시글 수
					pgvo = new PagingVO(pageNo, qty);
					log.info("pageNo>>"+pageNo+", qty>>"+qty);
				}
				
				// 검색어 받기
				String type = request.getParameter("type");
				String keyword = request.getParameter("keyword");
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				
				// 조건에 맞는 totalCount 가져오기
				int totalCount = bsv.getTotalCount(pgvo);
				log.info("게시글수 >> "+ totalCount);
				
				// pgvo정보 주고 limit 적용한 리스트 가져오기
				List<BoardVO> list = bsv.getList(pgvo);
				request.setAttribute("list", list);
				
				// 페이징 정보를 list.jsp로 보내기
				PagingHandler ph = new PagingHandler(pgvo, totalCount);
				request.setAttribute("ph", ph);
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "detail" :
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				isOk = bsv.upHit(bno);
				BoardVO bvo = bsv.getDetail(bno);
				request.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "modify" :
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = bsv.getDetail(bno);
				request.setAttribute("bvo", bvo);
				destPage = "/board/modify.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "update" :
			try {
				// 파일 임시 경로 저장
				savePath = getServletContext().getRealPath("_fileUpload");
				File filedir = new File(savePath);
				
				// 디스크에 기록할 파일 정보를 셋팅하는 객체
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(filedir); // 저장 경로 설정
				fileItemFactory.setSizeThreshold(3*1024*1024); // 임시저장 용량 설정
				
				BoardVO bvo = new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				
				String old_file = null; // 수정 전 원래 파일
				for(FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString("utf-8")));
						break;
					case "title":
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "img_file" :
						// 수정 이전 파일
						old_file = item.getString("utf-8");
						break;
					case "new_file" :
						// 새로운 파일이 있는지 확인
						if(item.getSize() > 0) {
							if(old_file != null) {
								// 기존 파일이 있으면 삭제
								FileHandler fileHandler = new FileHandler();
								isOk = fileHandler.deleteFile(old_file, savePath);
							}
							// new 파일의 경로와 파일명 생성
							String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
							log.info("new_fileName >>" + fileName);
							
							// 실제 저장될 파일이름
							fileName = System.currentTimeMillis()+"_"+fileName;
							File uploadeFilePath = new File(filedir + File.separator + fileName);
							// 저장
							try {
								item.write(uploadeFilePath);
								bvo.setImg_file(fileName);
								
								// 썸네일 작업
								Thumbnails.of(uploadeFilePath).size(60, 60).toFile(new File(filedir + File.separator + "_th_" + fileName));
								
							} catch (Exception e) {
								// TODO: handle exception
							}
						} else {
							bvo.setImg_file(old_file);
						}
						break;
					}
				}
				isOk = bsv.update(bvo);
				destPage = "list";
				;
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "remove" :
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				// 삭제할 파일이름 불러오기
				String fileName = bsv.getFileName(bno);
				
				// savePath 생성
				savePath = getServletContext().getRealPath("/_fileUpload");
				
				// 파일핸들러에게 삭제 요청
				FileHandler filehandler = new FileHandler();
				isOk = filehandler.deleteFile(fileName, savePath);
				
				isOk = bsv.remove(bno);
				destPage = "/brd/list";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
