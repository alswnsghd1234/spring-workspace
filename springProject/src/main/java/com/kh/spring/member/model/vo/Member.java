package com.kh.spring.member.model.vo;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*  lombok
	자동 코드 생성 라이브러리
	반복되는 getter, setter, toString 등의 메소드 작성 코드를 줄여주는 코드 라이브러리
	
	lombok 설치 방법
	1. 라이브러리 다운 후 적용(Mavn pom.xml)
	2. 다운로드 된 jar 파일을 찾아서 설치(작업할 IDE 선택)
	3. IDE 재실행
	
	lombok 사용시 주의사항
	-uName, bTitle과 같이 앞 글자가 소문자인 외자 필드명은 작성 불가
	-필드명 작성 시 소문자 두 글자 이상으로 작성해야함
	이유) el표기법 사용 시 내부적으로 getter 메소드를 찾게되는데 이 때 getuName(), getbTitle() 이라는 이름으로 메소드를 호출하기 때문
		기존 사용하는 방식이라면 getUName()이 되기 때문에 호출 불가
*/

@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자
@Setter
@Getter
@ToString
@EqualsAndHashCode // equals, hashcode
@Data // 위에 있는 모든 메소드를 포함하는 Annotation

public class Member {

	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private String age;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
}