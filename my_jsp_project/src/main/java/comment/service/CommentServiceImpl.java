package comment.service;

import java.util.List;

import comment.DAO.CommentDAO;
import comment.DAO.CommentDAOImpl;
import comment.domain.CommentVO;

public class CommentServiceImpl implements CommentService{

	private CommentDAO cdao;
	
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}


	@Override
	public int post(CommentVO cvo) {
		return cdao.insert(cvo);
	}


	@Override
	public List<CommentVO> getList(int cmt_bno) {
		return cdao.getList(cmt_bno);
	}


	@Override
	public int modify(CommentVO cvo) {
		return cdao.modify(cvo);
	}


	@Override
	public int remove(int cno) {
		return cdao.remove(cno);
	}


}
