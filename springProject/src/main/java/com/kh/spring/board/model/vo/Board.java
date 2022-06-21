package com.kh.spring.board.model.vo;

import java.sql.Date;

public class Board {

	
	private int boardNo;//	BOARD_NO	NUMBER
	private String boardTitle;//	BOARD_TITLE	VARCHAR2(100 BYTE)
	private String boardWriter;//	BOARD_WRITER	VARCHAR2(4000 BYTE)
	private String boardContent;//	BOARD_CONTENT	VARCHAR2(4000 BYTE)
	private String originName;//	ORIGIN_NAME	VARCHAR2(100 BYTE)
	private String changeName;//	CHANGE_NAME	VARCHAR2(100 BYTE)
	private int count;//	COUNT	NUMBER
	private Date createDate;//	CREATE_DATE	DATE
	private int status;//	STATUS	VARCHAR2(1 BYTE)
	
	public Board() {
		super();
	}

	public Board(int boardNo, String boardTitle, String boardWriter, String boardContent, String originName,
			String changeName, int count, Date createDate, int status) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.originName = originName;
		this.changeName = changeName;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", originName=" + originName + ", changeName=" + changeName
				+ ", count=" + count + ", createDate=" + createDate + ", status=" + status + "]";
	}
	
	
}
