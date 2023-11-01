package member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import board.DAO.BoardDAO;
import board.DAO.BoardDAOImpl;
import board.domain.BoardVO;
import comment.DAO.CommentDAO;
import comment.DAO.CommentDAOImpl;
import comment.domain.CommentVO;
import member.DAO.MemberDAO;
import member.DAO.MemberDAOImpl;
import member.domain.MemberVO;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

	private MemberDAO mdao;
	private BoardDAO bdao;
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
		bdao = new BoardDAOImpl();
	}
	@Override
	public int join(MemberVO mvo) {
		return mdao.join(mvo);
	}
	@Override
	public MemberVO getLogin(MemberVO mvo) {
		return mdao.getLogin(mvo);
	}
	@Override
	public List<BoardVO> getBoardList(MemberVO loginmvo) {
		return bdao.getBoardList(loginmvo);
	}
	@Override
	public int lastLogin(String id) {
		return mdao.lastLogin(id);
	}
	@Override
	public int update(MemberVO mvo) {
		return mdao.update(mvo);
	}
	@Override
	public int remove(String id) {
		return mdao.remove(id);
	}
	@Override
	public List<MemberVO> getList() {
		return mdao.getList();
	}

}
