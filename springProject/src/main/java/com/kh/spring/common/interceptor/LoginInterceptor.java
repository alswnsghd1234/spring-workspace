package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

//public class LoginInterceptor extends HandlerInterceptorAdapter{
	
public class LoginInterceptor implements HandlerInterceptor{
	
	// preHandle : 전처리를 의미함. DispatcherServlet에서 컨트롤러를 호출하기 전에 낚아채는 메소드
	// postHandle : 후처리를 의미함. 컨트롤러에서 요청처리하고 DispatcherServlet으로 뷰 정보가 리턴되는 순간 낚아채는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 내가 원하는 조건이 있으면 controller로 요청하기 전에 이 조건을 확인
		// 조건에 부합하면 true, 아니면 false를 반환
		// true일 때 controller 요청 실행
		// false일 때 controller 실행 X
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser")!=null) {
			return true;
		}
		else {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
			return false;
		}
	}
}