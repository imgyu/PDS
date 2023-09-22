package com.green.pds.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.pds.dao.PdsDao;
import com.green.pds.service.PdsService;
import com.green.pds.vo.FilesVo;
import com.green.pds.vo.PdsPagingVo;
import com.green.pds.vo.PdsVo;

@Service("pdsService")
public class PdsServiceImpl implements PdsService {

	@Autowired
	private   PdsDao   pdsDao;

	@Override
	public List<PdsVo> getPdsList(String menu_id) {
		
		List<PdsVo>  pdsList  = pdsDao.getPdsList( menu_id );
				
		return       pdsList;
	}

	@Override
	public PdsVo getPds(HashMap<String, Object> map) {
		
		PdsVo         pdsVo   = pdsDao.getPds( map );    
		return        pdsVo;
		
	}

	// 자료실 글 쓰기 + 파일저장
	@Override
	public void setWrite(
			HashMap<String, Object> map, 
			HttpServletRequest request) {
		
		// System.out.println("map1:" + map);
		
		// 1. request 처리 - 넘어온 파일을 d://upload// 에 저장
		PdsFile.save( map, request );  // 별도 class 생성
		
		// System.out.println("map2:" + map);
		
		// 2. 넘어온 정보 db 저장 (Board, Files)
		pdsDao.setWrite( map );
		
	}

	@Override
	public List<FilesVo> getFileList(HashMap<String, Object> map) {
		
		List<FilesVo>  fileList =  pdsDao.getFileList( map ); 
		return         fileList;
		
	}

	@Override
	public void setDelete(HashMap<String, Object> map) {
		
		// db 정보제거 : Files, Board
		pdsDao.setDelete( map );
		
		// 파일 삭제 map 에 삭제할 파일정보가 와야함
		List<FilesVo> fileList = (List<FilesVo>) map.get("fileList");
		PdsFile.delete( fileList );
		
	}

	@Override
	public void deleteUploadFile(HashMap<String, Object> map) {
		
		// d:\\upload\\ sfilename 에 해당되는 파일을 삭제
		
		String      filepath   =  "D:\\upload\\";   
		String      sfilename  =  (String) map.get("sfile");
		
		File  file  = new File( filepath + sfilename  );
		if( file.exists()  )
		   file.delete();
		
		// Files table  file_num 번 자료를 삭제		
		pdsDao.deleteUploadFile( map );
				
	}

	@Override
	public void setUpdate(
			HashMap<String, Object> map,
			HttpServletRequest request) {
		
		// 1. request 넘어온 파일만 저장 d:\\upload\\
		PdsFile.save(map, request);
		
		// 2.db 에 정보를 저장 : Board, Files		
		pdsDao.setUpdate( map );
		
	}
	
	// paging 처리를 위해 getPdsList() 수정
	@Override
	public List<PdsPagingVo> getPdsPagingList(HashMap<String, Object> map) {
		// mao 
		// menu_id, nowpage
		// pagecount, startnum, endnum
		// System.out.println("123:" + map);
		
		// 필요한 자료를 조회( 10개 rows 만)
		List<PdsPagingVo>  pdsPagingList = pdsDao.getPdsPagingList( map );
		
		int  pagetotalcount =  3;  // paging.jsp 페이지 번호 출력 갯수
		//                          pagetotalcount
		// 1 2 3 4 5 6 7 8 9 10   :  10
		// 1 2 3 4 5                  5 
				
		int        nowpage        =  Integer.parseInt( String.valueOf( map.get("nowpage") ) );    // 현재 페이지
		int        pagecount      =  Integer.parseInt( String.valueOf( map.get("pagecount")) );  // 한페이지에 보여줄 자료수
		
		// menu_id 에 해당되는 전체 자료수 - pdsDaoImpl 가 돌려준 map 에 저장
		int        totalcount     =  Integer.parseInt( String.valueOf( map.get("totalcount") ) );   
	
		String     menu_id  =  String.valueOf(map.get("menu_id")); 
		BoardPaging   bp    =  new BoardPaging(
			menu_id, nowpage, pagecount, totalcount, pagetotalcount);

		PdsPagingVo   vo    =  bp.getPdsPagingInfo();
		
		map.put("pdsPagingVo", vo );
		
		// System.out.println("124:vo" + vo);
		
		return     pdsPagingList;
	}
	
	

}
















