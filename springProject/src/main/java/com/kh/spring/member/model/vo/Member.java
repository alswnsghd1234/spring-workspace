package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/*
 * lombok
 * - 자동 코드 생성 라이브러리
 * - 반복되는 getter , setter, toString 등의 
 * 
 * 
 * */

@Getter
@Setter
public class Member {
	
	private String userId; //	USER_ID	VARCHAR2(30 BYTE)
	private String userPwd;//	USER_PWD	VARCHAR2(100 BYTE)
	private String userName;//	USER_NAME	VARCHAR2(15 BYTE)
	private String email;//	EMAIL	VARCHAR2(100 BYTE)
	private String gender;//	GENDER	VARCHAR2(1 BYTE)
	private String age;//	AGE	NUMBER
	private String phone;//	PHONE	VARCHAR2(13 BYTE)
	private String address;//	ADDRESS	VARCHAR2(100 BYTE)
	private Date enrollDate;//	ENROLL_DATE	DATE
	private Date modifyDate;//	MODIFY_DATE	DATE
	private String status;//	STATUS	VARCHAR2(1 BYTE)


	
	
	
}
