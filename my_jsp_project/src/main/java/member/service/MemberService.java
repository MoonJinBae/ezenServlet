package member.service;

import java.util.List;

import board.domain.BoardVO;
import comment.domain.CommentVO;
import member.domain.MemberVO;

public interface MemberService {

	int join(MemberVO mvo);

	MemberVO getLogin(MemberVO mvo);

	List<BoardVO> getBoardList(MemberVO loginmvo);

	int lastLogin(String id);

	int update(MemberVO mvo);

	int remove(String id);

	List<MemberVO> getList();


}
