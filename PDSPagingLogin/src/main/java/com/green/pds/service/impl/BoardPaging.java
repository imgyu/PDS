package com.green.pds.service.impl;

import com.green.pds.vo.PdsPagingVo;

public class BoardPaging {

	// Fields
	private   String  menu_id;
	private   int     nowpage;
	private   int     pagecount;
	private   int     totalcount;
	private   int     pagetotalcount;
	
	// 생성자
	public BoardPaging(
			String menu_id, int nowpage, int pagecount, int totalcount, int pagetotalcount) {
		this.menu_id          =  menu_id;
		this.nowpage          =  nowpage;
		this.pagecount        =  pagecount;
		this.totalcount       =  totalcount;
		this.pagetotalcount   =  pagetotalcount;
	}

	// method
	public PdsPagingVo getPdsPagingInfo() {
		
		PdsPagingVo  vo             =  new PdsPagingVo();

		// 한페이지보여줄 자료수  : pagecount
		// 전체 자료수            : totalcount
		// 총 페이지 수           : totalpagecount
		int          totalpagecount = 
				(int) Math.ceil( (double) totalcount / (double) pagecount );

		// paging.jsp 에서 사용할 페이지 번호 목록의 시작번호 :  pagestartnum
		// paging.jsp 에서 사용할 페이지 번호 목록의 끝번호   :  pageendnum
		int          pagestartnum   =  (nowpage-1)  /  pagetotalcount * pagetotalcount + 1;
		int          pageendnum     =  ((nowpage-1) /  pagetotalcount + 1) * pagetotalcount;		
		if( pageendnum > totalpagecount  )
			pageendnum  =  totalpagecount;
					
		vo.setMenu_id( menu_id );
		vo.setPagestartnum( pagestartnum );
		vo.setPageendnum( pageendnum );
		vo.setTotalpagecount( totalpagecount );
		
		return  vo;
		
	}
	
	// 

}








