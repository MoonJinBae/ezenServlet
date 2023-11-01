package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import board.domain.BoardVO;
import comment.domain.CommentVO;
import member.domain.MemberVO;
import member.service.MemberService;
import member.service.MemberServiceImpl;

@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private MemberService msv;
	private String destPage;
	private int isOk;
	private RequestDispatcher rdp;
	
    public MemberController() {
        msv = new MemberServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		
		switch (path) {
		case "index" :
			
			destPage = "/index.jsp";
			break;
		case "join":
			destPage = "/member/join.jsp";
			break;
		case "register":
			try {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				
				MemberVO mvo = new MemberVO(id, pw, email, age);
				isOk = msv.join(mvo);
				log.info("회원가입 " + (isOk > 0 ? "OK" : "FAIL"));
				destPage = "/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "login" :
			try {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				MemberVO mvo = new MemberVO(id, pw);
				
				MemberVO loginmvo = msv.getLogin(mvo);
				destPage = "/index.jsp";
				
				if(loginmvo != null) {
					// 연결된 세션이 있다면 세션 가져오기, 없다면 새로 생성
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginmvo);
					ses.setMaxInactiveInterval(10 * 60);
					// 로그인 id와 일치하는 게시글 목록 가져오기
					List<BoardVO> boardList = msv.getBoardList(loginmvo);
					request.setAttribute("loginBoardList", boardList);
				} else {
					// 일치하는 로그인 객체가 없다면
					request.setAttribute("msg_login", 0);
				}
				destPage = "/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "logout" :
			try {
				// 연결된 세션이 있다면 해당 세션 가져오기
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String id = mvo.getId();
				isOk = msv.lastLogin(id);
				ses.invalidate();
				destPage = "/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "modify" :
			destPage = "/member/modify.jsp";
			break;
		case "update" :
			try {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String email = request.getParameter("email");
				MemberVO mvo = new MemberVO(id, pw, email);
				
				isOk = msv.update(mvo);
				destPage = "logout";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "remove" :
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO) ses.getAttribute("ses");
				String id = mvo.getId();
				isOk = msv.remove(id);
				destPage = "logout";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "list" :
			try {
				List<MemberVO> list = msv.getList();
				request.setAttribute("mvo", list);
				destPage = "/member/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}

}
