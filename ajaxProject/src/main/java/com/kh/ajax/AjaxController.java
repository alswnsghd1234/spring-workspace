package com.kh.ajax;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

	
	
	//1.HttpServletResponse 객체로 응답데이터 응답하기(jsp/servlet때 했떤 stream을 이용한 방식)
	
//	@RequestMapping("ajax1.do")
//	public void ajaxMethod1(String name,int age,HttpServletResponse response) throws IOException {
//		
//		System.out.println(name);
//		System.out.println(age);
//		
//		//요청처리를 햇다는 가정하에 진행
//		String reponseData = "응답문자열 :"+name+"님은"+age+"살 입니다.";
//		
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(reponseData);
//		
//		//2. 응답할 데이터를 문자열로 반환
//		//단 문자열을 리턴하면 포워딩방식으로 인식하기 때문에 내가 응답하는 문자열이 포워딩이 아닌 응답데이터라는 것을 알려야함.
//		//응답데이터라는 어노테이션을 추가해야한다.
//		//@@ResponseBody
//		
//		}
	@ResponseBody
	@RequestMapping(value="ajax1.do",produces="text/html; charset=UTF-8")
	public String ajaxMethod1(String name,int age) {
		
		String responseData = name + age;
		
		PrintWriter out = response.getWriter();
		
		System.out.println(name);
		System.out.println(age);
		
		return responseData;
	}
	//3. 유효성 검사
	@RequestMapping
	public void ajaxMethod2(String name,int age) {
		
		
		int idChcek = dao.checkId(name);
		
		if (idChcek == 0) {
			System.out.println("사용중");
		} else if (idChcek == 1) {
			System.out.println("미 사용중");
		}
				
		out.write(idChcek + ""); 
		}
	
	
}
