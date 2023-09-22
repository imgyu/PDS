package com.green.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.board.dao.BoardDao;
import com.green.board.vo.BoardVo;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private  SqlSession  sqlSession;
	
	@Override
	public List<BoardVo> getBoardList( String menu_id  ) {
		
		List<BoardVo>  boardList =  sqlSession.selectList("Board.BoardList", menu_id);
		
		return     boardList;
	}

	@Override
	public void insertBoard(BoardVo vo) {
		
		int bnum  =  vo.getBnum();
		if( bnum == 0  ) {  // 새글 쓰기
			sqlSession.insert("Board.BoardInsert", vo);
		} else {  // 답글 쓰기
			sqlSession.update("Board.StepUpdate",  vo); // 기존글 step + 1
			sqlSession.insert("Board.BoardReply",  vo);  // 답글 추가
		}
		
	}

	@Override
	public BoardVo getBoard(int idx) {
		
		BoardVo boardVo  = sqlSession.selectOne("Board.BoardGet", idx );				
		
		return  boardVo;
	}

	@Override
	public void deleteBoard(BoardVo vo) {
		
		sqlSession.delete( "Board.BoardDelete",   vo );   // delnum = 1
		int  childCnt = sqlSession.selectOne("Board.childCnt", vo);
		// System.out.println("childCnt:" + childCnt);
		if(  childCnt == 0  )
			sqlSession.delete( "Board.BoardDelete2",  vo );   // delete 자식이 없으면
		
	}

	@Override
	public void incrementReadCount(int idx) {
		
		sqlSession.update("Board.UpdateReadCount", idx);
		
	}

	@Override
	public void updateBoard(BoardVo vo) {
		
		sqlSession.update("Board.UpdateBoard", vo);
		
	}

}






