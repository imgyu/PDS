package com.green.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.board.dao.BoardDao;
import com.green.board.service.BoardService;
import com.green.board.vo.BoardVo;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private   BoardDao  boardDao;
	
	@Override
	public List<BoardVo> getBoardList( String menu_id ) {
		
		List<BoardVo>  boardList  =  boardDao.getBoardList( menu_id  );
		
		return    boardList;
	}

	@Override
	public void insertBoard(BoardVo vo) {
		
		boardDao.insertBoard( vo );
		
	}

	@Override
	public BoardVo getBoard(int idx) {
		
		BoardVo  boardVo  =  boardDao.getBoard( idx );
		
		return   boardVo;
	}

	@Override
	public void deleteBoard(BoardVo vo) {
		
		boardDao.deleteBoard( vo );
		
	}

	@Override
	public void incrementReadCount(int idx) {
		
		boardDao.incrementReadCount( idx );
		
	}

	@Override
	public void updateBoard(BoardVo vo) {
		
		boardDao.updateBoard( vo );
		
	}


}







