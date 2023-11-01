package comment.service;

import java.util.List;

import comment.domain.CommentVO;

public interface CommentService {

	int post(CommentVO cvo);

	List<CommentVO> getList(int cmt_bno);

	int modify(CommentVO cvo);

	int remove(int cno);

}
