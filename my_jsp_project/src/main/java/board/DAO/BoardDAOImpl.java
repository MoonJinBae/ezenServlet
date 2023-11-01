package board.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import board.domain.BoardVO;
import member.domain.MemberVO;
import orm.DataBaseBuillder;
import paging.domain.PagingVO;

public class BoardDAOImpl implements BoardDAO {

	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	private int isOk;
	// DB와 연결
	private SqlSession sql;
	private final String NS = "BoardMapper.";
	
	public BoardDAOImpl() {
		new DataBaseBuillder();
		sql = DataBaseBuillder.getFactory().openSession();
	}
	
	@Override
	public int insert(BoardVO bvo) {
		isOk = sql.insert(NS+"writing", bvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("list DAO");
		return sql.selectList(NS+"list", pgvo);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info("list COUNT");
		return sql.selectOne(NS+"cnt", pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		return sql.selectOne(NS+"detail", bno);
	}

	@Override
	public int upHit(int bno) {
		isOk = sql.update(NS+"hit", bno);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int update(BoardVO bvo) {
		isOk = sql.update(NS+"update", bvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public String getFileName(int bno) {
		return sql.selectOne(NS+"fileName", bno);
	}

	@Override
	public int remove(int bno) {
		isOk = sql.delete(NS+"del", bno);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> getBoardList(MemberVO loginmvo) {
		return sql.selectList(NS+"loginList", loginmvo);
	}

}
