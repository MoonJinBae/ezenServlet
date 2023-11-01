package member.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import member.domain.MemberVO;
import orm.DataBaseBuillder;

public class MemberDAOImpl implements MemberDAO {
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);

	private SqlSession sql;
	private int isOk;
	private final String NS = "MemberMapper.";
	public MemberDAOImpl() {
		new DataBaseBuillder();
		sql = DataBaseBuillder.getFactory().openSession();
	}
	
	@Override
	public int join(MemberVO mvo) {
		isOk = sql.insert(NS+"join",mvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public MemberVO getLogin(MemberVO mvo) {
		return sql.selectOne(NS+"login", mvo);
	}

	@Override
	public int lastLogin(String id) {
		isOk = sql.update(NS+"lastLogin", id);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int update(MemberVO mvo) {
		isOk = sql.update(NS+"update", mvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int remove(String id) {
		isOk = sql.delete(NS+"del", id);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<MemberVO> getList() {
		return sql.selectList(NS+"list");
	}


}
