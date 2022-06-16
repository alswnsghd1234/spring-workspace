package com.kh.spring.member.model.vo;

import java.sql.Date;

public class Member {
//	
	private String userId; //	USER_ID	VARCHAR2(30 BYTE)
	private String userPwd;//	USER_PWD	VARCHAR2(100 BYTE)
	private String userName;//	USER_NAME	VARCHAR2(15 BYTE)
	private String eamil;//	EMAIL	VARCHAR2(100 BYTE)
	private String gender;//	GENDER	VARCHAR2(1 BYTE)
	private int age;//	AGE	NUMBER
	private String phone;//	PHONE	VARCHAR2(13 BYTE)
	private String address;//	ADDRESS	VARCHAR2(100 BYTE)
	private Date enrollDate;//	ENROLL_DATE	DATE
	private Date modifyDate;//	MODIFY_DATE	DATE
	private String status;//	STATUS	VARCHAR2(1 BYTE)
	
	public Member() {
		super();
	}

	public Member(String userId, String userPwd, String userName, String eamil, String gender, int age, String phone,
			String address, Date enrollDate, Date modifyDate, String status) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.eamil = eamil;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.address = address;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEamil() {
		return eamil;
	}

	public void setEamil(String eamil) {
		this.eamil = eamil;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", eamil=" + eamil
				+ ", gender=" + gender + ", age=" + age + ", phone=" + phone + ", address=" + address + ", enrollDate="
				+ enrollDate + ", modifyDate=" + modifyDate + ", status=" + status + "]";
	}
	
	
}
