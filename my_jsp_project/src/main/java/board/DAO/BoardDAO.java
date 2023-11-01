package board.DAO;

import java.util.List;

import board.domain.BoardVO;
import member.domain.MemberVO;
import paging.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int upHit(int bno);

	int update(BoardVO bvo);

	String getFileName(int bno);

	int remove(int bno);

	List<BoardVO> getBoardList(MemberVO loginmvo);

}
