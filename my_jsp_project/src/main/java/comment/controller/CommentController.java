package comment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comment.domain.CommentVO;
import comment.service.CommentService;
import comment.service.CommentServiceImpl;

@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
	private int isOk;
	private CommentService csv;
	
    public CommentController() {
    	csv = new CommentServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// response의 setContentType 설정은 하지 않음.
		
		String uri = request.getRequestURI();
		//  /brd/detail?bno=1; => 동기방식의 주소
		// 동기방식 => get, post
		//  /cmt/list/10  , /cmt/post  , /cmt/update  => RestAPI
		// get => 리스트 보여줄 때 , post => 등록시, put => update, delete => delete
		String pathUri = uri.substring("/cmt/".length()); // post, list/10 ....
		String path = pathUri;
		String pathVar = "";
		if(pathUri.contains("/")) {
			path = pathUri.substring(0,pathUri.lastIndexOf("/")); // list
			pathVar = pathUri.substring(pathUri.lastIndexOf("/")+1); // 10
		}
		log.info("uri >> "+uri);
		log.info("pathUri >> "+pathUri);
		log.info("path >> "+path);
		log.info("pathVar >> "+pathVar);
		
		switch (path) {
		case "post":
			try {
				// JSON 방식으로 화면에서 보낸 데이터를 받을 준비
				// String 형태로 값을 받아서 객체로 변화 JSON
				// json-simple-1.1.1.jar 라이브러리를 사용하여 JSON 형태의 스트링을 객체 형태로 변환
				StringBuffer sb = new StringBuffer();
				// append
				String line = "";
				BufferedReader br = request.getReader(); // cmtData를 받아오는 객체
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				log.info("sb >> " + sb.toString());
				
				// 객체로 변환
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				// CommentVO 형태로 변환
				int cmt_bno = Integer.parseInt(jsonObj.get("bno").toString());
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				
				// csv DB로 저장
				CommentVO cvo = new CommentVO(cmt_bno, writer, content);
				isOk = csv.post(cvo);
				log.info("댓글등록 >> "+ (isOk > 0 ? "ok" : "fail"));
				// 화면에 출력
				PrintWriter out = response.getWriter();
				out.print(isOk);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "list" : //   list/471
			try {
				int cmt_bno = Integer.parseInt(pathVar);
				List<CommentVO> list = csv.getList(cmt_bno);
				log.info("list >> "+ list);
				
				// JSON 형태로 변환 -> 화면에 전송
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				// (3) [{…}, {…}, {…}]
				
				JSONArray jsonList = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					jsonObjArr[i] = new JSONObject(); // key:value
					jsonObjArr[i].put("cno", list.get(i).getCno());
					jsonObjArr[i].put("bno", list.get(i).getCmt_bno());
					jsonObjArr[i].put("writer", list.get(i).getWriter());
					jsonObjArr[i].put("content", list.get(i).getContent());
					jsonObjArr[i].put("regdate", list.get(i).getRegdate());
					jsonList.add(jsonObjArr[i]);
				}
				String jsonData = jsonList.toJSONString(); // 전송용
				
				// 전송 객체에 싣고 화면으로 전송
				PrintWriter out = response.getWriter();
				out.print(jsonData);
				
			} catch (Exception e) {
				log.info("comment >> list >> error");
				e.printStackTrace();
			}
			break;
		case "modify" :
			try {
				StringBuffer sb = new StringBuffer();
				String line = "";
				
				BufferedReader br = request.getReader();
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				log.info("sb >> " + sb.toString());
				
				// JSON 형태의 객체로 변환
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				int cno = Integer.parseInt(jsonObj.get("cno").toString());
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				
				CommentVO cvo = new CommentVO(cno, content);
				isOk = csv.modify(cvo);
				
				PrintWriter out = response.getWriter();
				out.print(isOk);
				log.info("modify >> " + (isOk > 0 ? "ok" : "fail"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "remove" :
			try {
				int cno = Integer.parseInt(pathVar);
				log.info("cno >> " + cno);
				isOk = csv.remove(cno);
				
				PrintWriter out = response.getWriter();
				out.print(isOk);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
