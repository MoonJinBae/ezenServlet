package comment.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import comment.domain.CommentVO;
import member.domain.MemberVO;
import orm.DataBaseBuillder;

public class CommentDAOImpl implements CommentDAO {

	private SqlSession sql;
	private final String NS = "CommentMapper.";
	private int isOk;
	public CommentDAOImpl() {
		new DataBaseBuillder();
		sql = DataBaseBuillder.getFactory().openSession();
	}
	
	@Override
	public int insert(CommentVO cvo) {
		isOk = sql.insert(NS+"add",cvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<CommentVO> getList(int cmt_bno) {
		return sql.selectList(NS+"list",cmt_bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		isOk = sql.update(NS+"update", cvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int remove(int cno) {
		isOk = sql.delete(NS+"del", cno);
		if(isOk > 0) sql.commit();
		return isOk;
	}

}
