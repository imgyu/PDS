package com.green.pds.vo;

public class PdsPagingVo {
	
	// Fields
	// Board
	private  int      idx;
	private  String   title;
	private  String   cont;
	private  String   regdate;
	private  String   writer;
	private  int      readcount;
	
	private  int      filescount;
	
	private  int      bnum;
	private  int      lvl;
	private  int      step;
	private  int      nref;
	private  int      delnum;
	private  int      parent;
	
	// Menus
	private  String   menu_id;
	private  String   menu_name;
	private  int      menu_seq;
	
	// Files
	private  int      file_num; 
	private  String   filename; 
	private  String   fileext; 
	private  String   sfilename;
	
	// paging 처리를 위한 추가 정보
	// sql 문을 위한 변수
	private  int      nowpage;          // 현재 페이지
	private  int      pagecount;        // 현재 페이지에 보여줄 row 수
	private  int      totalcount;       // 전체 자료(row)수
	
	// paging.jsp 가 쓸 변수
	private  int      totalpagecount;   // 화면에 보여줄 페이지 수
	private  int      pagestartnum;     // 페이지 시작 번호
	private  int      pageendnum;       // 페이지 끝   번호
	
	// Constructor
	public PdsPagingVo() {}
	public PdsPagingVo(int idx, String title, String cont, String regdate, String writer, int readcount, int filescount,
			int bnum, int lvl, int step, int nref, int delnum, int parent, String menu_id, String menu_name,
			int menu_seq, int file_num, String filename, String fileext, String sfilename, int nowpage, int pagecount,
			int totalcount, int totalpagecount, int pagestartnum, int pageendnum) {
		this.idx = idx;
		this.title = title;
		this.cont = cont;
		this.regdate = regdate;
		this.writer = writer;
		this.readcount = readcount;
		this.filescount = filescount;
		this.bnum = bnum;
		this.lvl = lvl;
		this.step = step;
		this.nref = nref;
		this.delnum = delnum;
		this.parent = parent;
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.menu_seq = menu_seq;
		this.file_num = file_num;
		this.filename = filename;
		this.fileext = fileext;
		this.sfilename = sfilename;
		this.nowpage = nowpage;
		this.pagecount = pagecount;
		this.totalcount = totalcount;
		this.totalpagecount = totalpagecount;
		this.pagestartnum = pagestartnum;
		this.pageendnum = pageendnum;
	}
	
	// Getter / Setter
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getFilescount() {
		return filescount;
	}
	public void setFilescount(int filescount) {
		this.filescount = filescount;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getNref() {
		return nref;
	}
	public void setNref(int nref) {
		this.nref = nref;
	}
	public int getDelnum() {
		return delnum;
	}
	public void setDelnum(int delnum) {
		this.delnum = delnum;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getMenu_seq() {
		return menu_seq;
	}
	public void setMenu_seq(int menu_seq) {
		this.menu_seq = menu_seq;
	}
	public int getFile_num() {
		return file_num;
	}
	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileext() {
		return fileext;
	}
	public void setFileext(String fileext) {
		this.fileext = fileext;
	}
	public String getSfilename() {
		return sfilename;
	}
	public void setSfilename(String sfilename) {
		this.sfilename = sfilename;
	}
	public int getNowpage() {
		return nowpage;
	}
	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getTotalpagecount() {
		return totalpagecount;
	}
	public void setTotalpagecount(int totalpagecount) {
		this.totalpagecount = totalpagecount;
	}
	public int getPagestartnum() {
		return pagestartnum;
	}
	public void setPagestartnum(int pagestartnum) {
		this.pagestartnum = pagestartnum;
	}
	public int getPageendnum() {
		return pageendnum;
	}
	public void setPageendnum(int pageendnum) {
		this.pageendnum = pageendnum;
	}

	// toString 
	@Override
	public String toString() {
		return "PdsPagingVo [idx=" + idx + ", title=" + title + ", cont=" + cont + ", regdate=" + regdate + ", writer="
				+ writer + ", readcount=" + readcount + ", filescount=" + filescount + ", bnum=" + bnum + ", lvl=" + lvl
				+ ", step=" + step + ", nref=" + nref + ", delnum=" + delnum + ", parent=" + parent + ", menu_id="
				+ menu_id + ", menu_name=" + menu_name + ", menu_seq=" + menu_seq + ", file_num=" + file_num
				+ ", filename=" + filename + ", fileext=" + fileext + ", sfilename=" + sfilename + ", nowpage="
				+ nowpage + ", pagecount=" + pagecount + ", totalcount=" + totalcount + ", totalpagecount="
				+ totalpagecount + ", pagestartnum=" + pagestartnum + ", pageendnum=" + pageendnum + "]";
	}

	
}



