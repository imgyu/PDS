package com.green.board.service;

import java.util.List;

import com.green.board.vo.BoardVo;

public interface BoardService {

	List<BoardVo> getBoardList(String menu_id);

	void insertBoard(BoardVo vo);

	BoardVo getBoard(int idx);

	void deleteBoard(BoardVo vo);

	void incrementReadCount(int idx);

	void updateBoard(BoardVo vo);

}
