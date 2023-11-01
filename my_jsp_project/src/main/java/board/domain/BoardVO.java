package board.domain;

public class BoardVO {
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private String moddate;
	private int hit;
	private String img_file;
	private int cmtCount;
	
	public BoardVO() {}
	
	// insert
	public BoardVO(String title, String writer, String content, String img_file) {
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.img_file = img_file;
	}

	// list
	public BoardVO(int bno, String title, String writer, String regdate, String moddate,int hit, String img_file,int cmtCount) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
		this.moddate = moddate;
		this.hit = hit;
		this.img_file = img_file;
		this.cmtCount = cmtCount;
	}

	// detail
	public BoardVO(int bno, String title, String writer, String content, String regdate, String moddate, int hit,
			String img_file) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.moddate = moddate;
		this.hit = hit;
		this.img_file = img_file;
	}

	// update
	public BoardVO(int bno, String title, String content, String img_file) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.img_file = img_file;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getModdate() {
		return moddate;
	}

	public void setModdate(String moddate) {
		this.moddate = moddate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getImg_file() {
		return img_file;
	}

	public void setImg_file(String img_file) {
		this.img_file = img_file;
	}

	
	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", moddate=" + moddate + ", hit=" + hit + ", img_file=" + img_file + ", cmtCount="
				+ cmtCount + "]";
	}

}
