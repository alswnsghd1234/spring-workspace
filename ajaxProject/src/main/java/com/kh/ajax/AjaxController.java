package com.kh.ajax;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.model.vo.Member;

@Controller
public class AjaxController {
	
	// 1. HttpServletResponse 객체로 응답 데이터 응답하기(jsp/servlet떄 했던 stream을 이용한 방식)
	
//	@RequestMapping("ajax1.do")
//	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
//		
//		System.out.println("name : " + name);
//		System.out.println("age : " + age);
//		
//		// 요청 처리를 했다는 가정 하에 진행
//		String responseData = "응답 문자열 : " + name + "님은 " + age + "살 입니다.";
//		
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(responseData);
//	}
	
	// 2. 응답할 데이터를 문자열로 반환
	// 단 문자열을 반환하면 포워딩 방식으로 인식하기 때문에 내가 응답하는 문자열이 포워딩이 아닌 응답 데이터라는 것을 알려야 함
	// 응답 데이터라는 어노테이션을 추가해야함
	//@ResponseBody
	
//	@ResponseBody
//	@RequestMapping(value="ajax1.do", produces="text/html; charset=UTF-8")
//	public String ajaxMethod1(String name, int age) {
//		
//		String responseData = "응답 문자열 : " + name + "님은 " + age + "살 입니다.";
//		
//		return responseData;
//	}
	
	// 3. 다수의 응답 데이터가 있을 경우
	
	/*@ResponseBody
	@RequestMapping("ajax1.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws Exception{
		
		// 요청 처리가 되었다는 가정 하에 진행
//		response.setContentType("text/html charset=UTF-8");
//		response.getWriter().print(name);
//		response.getWriter().print(age);
		
		// JSON (javaScript Object Notation)
		// JSONArray [value, value, value...] - 자바에서 ArrayList형식과 유사한 형태
		// JSONObject [key:value, key:value, key:value..] - 자바에서 HashMap형식과 유사한 형태
		
		// 방법1 JSONArray로 응답해보기
//		JSONArray jArr = new JSONArray();
//		jArr.add(name);
//		jArr.add(age);
//		
//		response.setContentType("application/json; charset=UTF-8");
//		response.getWriter().print(jArr);
		
		// 방법2 JSONObject로 응답하기
		JSONObject jObj = new JSONObject();
		jObj.put("name", name);
		jObj.put("age", age);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jObj);
	}*/
	
	@ResponseBody
	@RequestMapping(value="ajax1.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		
		JSONObject jObj = new JSONObject();
		jObj.put("name", name);
		jObj.put("age", age);
		
		return jObj.toJSONString();
	}
	
	/*@ResponseBody
	@RequestMapping(value="ajax2.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod2(int userNo) {
		
		Member m = new Member("user1", "user123", 20, "010-0000-1111");
		
		// JSON형태로 만들어서 응답
		JSONObject jObj = new JSONObject();
		jObj.put("userId", m.getUserId());
		jObj.put("userPwd", m.getUserPwd());
		jObj.put("age", m.getAge());
		jObj.put("phone", m.getPhone());
		
		return jObj.toJSONString();
	}*/
	@ResponseBody
	@RequestMapping(value="ajax2.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod2(int userNo) {
		
		Member m = new Member("user1", "user123", 20, "010-0000-1111");
		
		return new Gson().toJson(m);
	}
	@ResponseBody
	@RequestMapping(value="ajax3.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod3() {
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		list.add(new Member("user01", 20, "010-1111-2222"));
		list.add(new Member("user02", 21, "010-1111-2223"));
		list.add(new Member("user03", 22, "010-1111-2224"));
		list.add(new Member("user04", 23, "010-1111-2225"));
		list.add(new Member("user05", 24, "010-1111-2226"));
		list.add(new Member("user06", 25, "010-1111-2227"));
		
		return new Gson().toJson(list);
	}
}