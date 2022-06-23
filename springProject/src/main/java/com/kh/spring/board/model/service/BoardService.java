package com.kh.spring.board.model.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

public interface BoardService {

	
	//게시판 리스트 조회 + 페이징 처리
	//전체 게시글 수 구하기
	int selectListCount();
	
	//게시글 리스트 조회
	ArrayList<Board> selectList(PageInfo pi);
	
	//게시글 작성하기
	int insertBoard(Board b);
	
	//게시글 상세조회
	//게시글 조회수 증가
	int increaseCount(int boardNo);
	
	//게시글 상세조회
	public abstract Board selectBoard(int boardNo); 
	
	//게시글 삭제
	int deleteBoard(int boardNo);
	
	//게시글 수정
	int updateBoard(Board b);
	
	//댓글 리스트 조회
	ArrayList<Reply> selectReplyList(int boardNo);
	
	//댓글 작성
	int insertReply(Reply r);
}
