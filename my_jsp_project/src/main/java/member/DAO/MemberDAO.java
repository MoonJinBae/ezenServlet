package member.DAO;

import java.util.List;

import member.domain.MemberVO;

public interface MemberDAO {

	int join(MemberVO mvo);

	MemberVO getLogin(MemberVO mvo);

	int lastLogin(String id);

	int update(MemberVO mvo);

	int remove(String id);

	List<MemberVO> getList();



}
