package board.service;

import java.util.List;

import board.domain.BoardVO;
import paging.domain.PagingVO;

public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int upHit(int bno);

	int update(BoardVO bvo);

	String getFileName(int bno);

	int remove(int bno);

}
