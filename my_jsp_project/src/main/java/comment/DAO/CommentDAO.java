package comment.DAO;

import java.util.List;

import comment.domain.CommentVO;
import member.domain.MemberVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> getList(int cmt_bno);

	int modify(CommentVO cvo);

	int remove(int cno);


}
