package board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import board.DAO.BoardDAO;
import board.DAO.BoardDAOImpl;
import board.domain.BoardVO;
import comment.DAO.CommentDAO;
import comment.DAO.CommentDAOImpl;
import paging.domain.PagingVO;

public class BoardServiceImpl implements BoardService {
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	// BoardDAO 객체
	private BoardDAO bdao;
	// CommentDAO 객체
	private CommentDAO cdao;
	public BoardServiceImpl(){
		bdao = new BoardDAOImpl();
		cdao = new CommentDAOImpl();
	}
	@Override
	public int insert(BoardVO bvo) {
		return bdao.insert(bvo);
	}
	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("list service");
		return bdao.getList(pgvo);
	}
	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info("list service cnt");
		return bdao.getTotalCount(pgvo);
	}
	@Override
	public BoardVO getDetail(int bno) {
		return bdao.getDetail(bno);
	}
	@Override
	public int upHit(int bno) {
		return bdao.upHit(bno);
	}
	@Override
	public int update(BoardVO bvo) {
		return bdao.update(bvo);
	}
	@Override
	public String getFileName(int bno) {
		return bdao.getFileName(bno);
	}
	@Override
	public int remove(int bno) {
		return bdao.remove(bno);
	}

}
