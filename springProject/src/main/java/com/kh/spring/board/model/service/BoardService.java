package com.kh.spring.board.model.service;

import java.util.ArrayList;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

public interface BoardService {

	int selectListCount();
	
	ArrayList<Board> selectList(PageInfo pi);
	
	int insertBoard(Board b);
	
	int increaseCount(int boardNo);
	
	Board selectBoard(int boardNo);
	
	int updateBoard(Board b);
	
	int deleteBoard(int boardNo);
	
	ArrayList<Reply> selectReplyList(int boardNo);
	
	int insertReply(Reply r);
	
	ArrayList<Board> selectTopList();
}