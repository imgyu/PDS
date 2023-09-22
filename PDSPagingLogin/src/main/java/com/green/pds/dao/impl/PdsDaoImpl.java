package com.green.pds.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.pds.dao.PdsDao;
import com.green.pds.vo.FilesVo;
import com.green.pds.vo.PdsPagingVo;
import com.green.pds.vo.PdsVo;

@Repository("pdsDao")
public class PdsDaoImpl implements PdsDao {

	@Autowired
	private  SqlSession   sqlSession;
	
	@Override
	public List<PdsVo> getPdsList(String menu_id) {
		
		List<PdsVo>  pdsList =  sqlSession.selectList("Pds.PdsList", menu_id);
		return       pdsList;
		
	}

	@Override
	public PdsVo getPds(HashMap<String, Object> map) {
		// 조회수 증가 (Board table readcount++)
		sqlSession.update("Board.UpdateReadCount", map);
		
		// 자료조회
		PdsVo       pdsVo   =  sqlSession.selectOne("Pds.PdsGet", map ); 
		return      pdsVo;
		
	}

	@Override
	public void setWrite(HashMap<String, Object> map) {
		
		// db 정보 저장
		// Board  에 저장
		int  bnum = Integer.parseInt( (String) map.get("bnum") );
		if ( bnum == 0 ) {
			sqlSession.insert("Pds.PdsInsert", map); // 새글
		} else {
			sqlSession.update("Pds.StepUpdate", map); // 새글			
			sqlSession.insert("Pds.PdsReply", map); // 새글			
		}
		
		// Files  에 저장
		List<FilesVo>  fileList =  (List<FilesVo>) map.get("fileList");
		if( fileList.size() != 0  )
			sqlSession.insert("Pds.FileInsert", map);
		
	}

	@Override
	public List<FilesVo> getFileList(HashMap<String, Object> map) {
		
		List<FilesVo>  fileList  =  sqlSession.selectList("Pds.FileList", map); 
		
		return         fileList;
	}

	@Override
	public void setDelete(HashMap<String, Object> map) {
		
		// 자식이 있는지 확인한다 
		// 입력 : idx 비교 idx == parent
		// 0: 자식없음 - 삭제대상 
		// 1: 자식있음 - delnum <- 1 
		int  isChild = sqlSession.selectOne("Pds.isChild", map);
		if (isChild == 0) {
			// idx 에 해당하는 삭제할 파일목록 조회
			List<FilesVo>  fileList = getFileList(map);
			map.put("fileList", fileList  );		
			
			sqlSession.delete("Pds.FileDelete",  map);  // Files table 정보삭제
			sqlSession.delete("Pds.BoardDelete", map); // Board table 정보삭제
		} else {
			sqlSession.update("Pds.BoardDelete2", map); // 1: 자식있음 - delnum <- 1
		}
		

		
	}

	@Override
	public void deleteUploadFile(HashMap<String, Object> map) {
		
		sqlSession.delete("Pds.DeleteUploadFile", map);
		
	}

	@Override
	public void setUpdate(HashMap<String, Object> map) {
		
		// 1. Board table title, cont 를 수정
		sqlSession.update("Pds.PdsUpdate",  map);
		
		// 2. Files 테이블에 추가된 파일 정보를 저장
		List<FilesVo>  fileList  =  (List<FilesVo>) map.get("fileList");
		if( fileList.size() > 0 )
			sqlSession.insert("Pds.FileInsert2", map );
	}

	@Override
	public List<PdsPagingVo> getPdsPagingList(HashMap<String, Object> map) {
		
		// 전체 자료수 조회
		// System.out.println("128:" + map.get("menu_id") );
		int      totalcount              =  sqlSession.selectOne("Pds.GetTotalCount", map);
		map.put("totalcount",  totalcount);
		
		// 메뉴 목록 조회 (paging) : startnum ~ endnum
		List<PdsPagingVo> pdsPagingList  =  sqlSession.selectList("Pds.PdsPagingList", map);  
		
		return   pdsPagingList;
	}
	
	

}











