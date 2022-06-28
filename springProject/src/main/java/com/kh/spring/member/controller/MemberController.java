package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@Controller // Controller 타입의 Annotation을 붙여주면 bean scanner가 자동으로 bean 등록해줌
public class MemberController {
	
//	기존 객체 생성 방식
//	private MemberService memberService = new MemberService();
//	서비스가 동시에 많은 회수가 요청되면 그만큼 객체가 생성됨
//	객체간의 결합도가 높아짐(소스 코드의 수정이 있을 경우 해당 코드를 작성한 곳에 전부 수정해야함)
//	Spring의 DI(Dependency Injection) 객체를 생성해서 주입해줌
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
//	@RequestMapping(value="login.me") : RequestMapping 타입의 Annotation을 붙여서 HandlerMapping 등록
//	()안에 속성을 여러개 넣을거라면 속성명=값, 속성명=값 형태로 작성. Mapping값 1개만 작성한다면 값만 작성해도 됨
	
//	Spring에서 parameter(요청 시 전달값) 받는 방법
	
	// 1. HttpServletRequest를 이용해서 전달받기(기존 jsp/servlet 방식)
	// 해당 메소드의 매개변수로 HttpServletRequest를 작성하면 스프링 컨테이너가 해당 메소드를 호출할 때(실행) 자동으로 해당 객체를 생성해서 매개변수로 주입해줌
	
//	@RequestMapping("login.me")
//	public String loginMember(HttpServletRequest request) {
//		
//		String userId = request.getParameter("userId");
//		String userPwd = request.getParameter("userPwd");
//		
//		System.out.println("userId : " + userId);
//		System.out.println("userPwd : " + userPwd);
//		
//		return "main";
//	}
	
	// 2. @RequestParam Annotaion을 이용하는 방법
	// request.getParameter("key")로 값을 뽑는 역할을 대신 수행해주는 Annotation
	// value 속성의 value로 jsp에서 작성했던 name값을 입력해주면 알아서 매개변수로 담아옴
	// 만약 넘어온 값이 비어있다면 defaultValue로 기본값 설정도 가능
	
//	@RequestMapping("login.me")
//	public String loginMember(@RequestParam(value="userId") String userId,
//							  @RequestParam(value="userPwd") String userPwd) {
//		
//		System.out.println("userId : " + userId);
//		System.out.println("userPwd : " + userPwd);
//		
//		return "main";
//	}
	
	// 3. @RequestParam Annotation을 생략하는 방법
	// 단, 매개변수명을 jsp의 name 속성값(요청 시 전달한 key값)과 동일하게 설정해야함
	// 또한, 위에서 사용했던 defaultValue 속성 사용 불가
	
//	@RequestMapping("login.me")
//	public String loginMember(String userId, String userPwd) {
//		
//		Member m = new Member();
//		m.setUserId(userId);
//		m.setUserPwd(userPwd);
//		
//		return "main";
//	}
	
	// 4. commend 객체 방식
	// 해당 메소드의 매개변수로 요청 시 전달값을 담고자 하는 VO 클래스 타입으로 설정하고, 요청 시 전달값의 key값(jsp에서 넘겨주는 name)을 VO클래스에 담고자 하는 필드명으로 작성
	// 스프링 컨테이너가 해당 객체를 기본 생성자로 생성 후 내부적으로 setter 메소드를 찾아서 요청 시 전달한 값을 필드에 담아줌
	// 주의 : name 값과 필드명이 같아야 함
	
//	요청 처리 후 응답 데이터를 담고 응답 페이지로 포워딩 또는 url 재요청 하는 방법
	// 1. 스프링에서 제공하는 Model 객체를 이용하는 방법
	// 포위딩할 응답뷰로 전달하고자 하는 데이터를 맵형식(key-value)으로 담을 수 있는 영역
	// Model 객체는 requestScope
	// 단, setAttribute가 아닌 addAttribute()를 이용
	
//	@RequestMapping("login.me")
//	public String loginMember(Member m, HttpSession session, Model model) {
//		
//		Member loginUser = memberService.loginMember(m);
//		
//		if(loginUser == null) {
//			
//			model.addAttribute("errorMsg", "로그인 실패");
//			포워딩 방식(파일 경로를 포함한 파일명을 제시)
//			// servlet-context.xml의 주소 자동 완성 도구 : view  resolver
//			
//			return "common/errorPage";
//		}
//		else {
//			
//			session.setAttribute("loginUser", loginUser);
//			
//			return "redirect:/";
//		}
//	}
	
	// 2. 스프링에서 제공하는 ModelAndView 객체를 이용하는 방법
	// Model은 데이터를 key-value 세트로 담을 수 있다면 View는 응답뷰에 대한 정보를 담을 수 있음
	// 이 경우에는 리턴 타입(반환형)이 String이 아닌 modelAndView 형태로 반환해야함
	
	// Model과 View와 결합된 형태의 객체
	// Model은 단독 사용 가능하지만 View는 불가
	
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
		
		/*Member loginUser = memberService.loginMember(m);
		
		if(loginUser == null) {
			
			mv.addObject("errorMsg", "로그인 실패");
			
			mv.setViewName("common/errorPage");
		}
		else {
			
			session.setAttribute("loginUser", loginUser);
			
			mv.setViewName("redirect:/");
		}
		
		return mv;*/
		
		// 암호화 작업 후
		// 기존의 로그인 방식에서는 아이디와 비밀번호를 비교해서 일치하면 로그인이 가능하게 했지만, 비밀번호가 암호화되어서 바뀌었기 때문에 사용 불가
		
		Member loginUser = memberService.loginMember(m);
		// m.getUserPwd()는 암호화되지 않은 비밀번호
		
		// bcryptPasswordEncoder 객체의 matches 메소드를 사용하여 평문 비밀번호와 암호화된 비밀번호를 비교하는 구문을 작성하면됨
		// matches(평문, 암호문)을 작성하면 암호화된 비밀번호는 자동적으로 복호화되어 평문의 비밀번호와 비교하여 boolean으로 반환함
		if(loginUser!=null && bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			
			session.setAttribute("alertMsg", loginUser.getUserName() + "님 환영합니다.");
			session.setAttribute("loginUser", loginUser);
			
			mv.setViewName("redirect:/");
		}
		else {
			
			mv.addObject("errorMsg", "로그인 실패");
			
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		
		return "member/memberEnrollForm";
	}
	
	@RequestMapping("insert.me")
	public ModelAndView insertMember(Member m, HttpSession session, ModelAndView mv) {
		
//		1. 한글 깨짐 문제 -> web.xml에 스프링에서 제공하는 인코딩 필터 등록
//		2. 나이를 입력하지 않았을 때 int 자료형에 빈 문자열이 들어갈 수 없어서 나는 400 오류 -> Member vo에 age 필드를 String 자료형으로 변경
//		3. 사용자가 입력한 비밀번호가 그대로(평문)이기 때문에 보안 문제 -> Bcrypt 방식의 암호화를 통해서 pwd를 암호문으로 변경
		// 1) spring security 모듈에서 제공하는 라이브러리 3개 pom.xml에 등록
		// 2) BCryptPasswordEncoder 클래스를 xml 파일에 bean 등록
		// 3) web.xml에서 로딩할 수 있게 작성
		
		// 암호화 작업
		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		
		m.setUserPwd(encPwd);
		
		int result = memberService.insertMember(m);
		
		if(result > 0) {
			
			session.setAttribute("alertMsg", "회원 가입 성공");
			session.setAttribute("loginUser", m);
			
			mv.setViewName("redirect:/");
			
		}
		else {
			
			mv.addObject("errorMsg", "회원가입 실패");
			
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	@RequestMapping("myPage.me")
	public String myPage() {
		
		return "member/myPage";
	}
	
	@RequestMapping("update.me")
	public ModelAndView updateMember(Member m, HttpSession session, ModelAndView mv) {
		
		int result = memberService.updateMember(m);
		
		if(result > 0) {
			
			Member updateMem = memberService.loginMember(m);
			session.setAttribute("alertMsg", "회원 정보 수정 성공");
			session.setAttribute("loginUser", updateMem);
			
			mv.setViewName("redirect:myPage.me");
		}
		else {
			
			mv.addObject("errorMsg", "회원가입 실패");
			
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping("delete.me")
	public ModelAndView deleteMember(String userPwd, HttpSession session, ModelAndView mv) {
		
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser!=null && bcryptPasswordEncoder.matches(userPwd, loginUser.getUserPwd())) {
			
			int result = memberService.deleteMember(loginUser.getUserId());
			
			if(result > 0) {
				
				session.setAttribute("alertMsg", "회원 탈퇴 성공");
				session.removeAttribute("loginUser");
				mv.setViewName("redirect:./");
			}
			
			else {
				
				mv.addObject("errorMsg", "회원 탈퇴 실패");
				mv.setViewName("common/errorPage");
			}
		}
		
		else {
			
			session.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			mv.setViewName("redirect:myPage.me");
		}
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="idcheck.me", produces="text/html; charset=UTF-8")
	public String idCheck(String userId, Model model) {
		
		int result = memberService.idCheck(userId);
		
		return String.valueOf(result);
	}
}