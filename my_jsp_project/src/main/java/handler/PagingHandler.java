package handler;

import paging.domain.PagingVO;

public class PagingHandler {
	private int startPage; // 현재 화면에서 보여줄 시작 페이지네이션 번호
	private int endPage; //현재 화면에서 보여줄 마지막 페이지네이션 번호
	private int pageCount = 5; // 한 페이지에 나타낼 페이지 번호 개수
	private int realEndPage; // 전체 페이지의 끝 번호
	private boolean prev, next; // 이전, 다음 페이지 존재 여부
	private int totalCount; // 전체 게시글 수
	
	private PagingVO pgvo;
	
	public PagingHandler() {}
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)pageCount) * pageCount;
		this.startPage = endPage - 4;
		
		this.realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		if(this.realEndPage < this.endPage) 
			this.endPage = this.realEndPage;
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.realEndPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRealEndPage() {
		return realEndPage;
	}
	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public PagingVO getPgvo() {
		return pgvo;
	}
	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}

}
