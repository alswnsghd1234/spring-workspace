package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;
@Controller
public class MemberController {
	
	//private MemberService memberService = new MemberServiceImpl();
	
	/*
	 * 기존 객체 생성 방식
	 * 서비스가 동시에 많은 회수가 요청되면 그만큼 객체가 생성된다
	 * 객체간의 결합도가 높아진다 (소스코드의 수정이 일어날경우 해당코드를 작성한곳에 전부 수정이 이루어져야한다)
	 * Spring의 DI(Dependency injection)객체를 생성해서 주입해준다.
	 *
	 * new 연산자를 쓰지 않고 선언만 해도 되지만 @Autowired 어노테이션을 작성해줘야한다.
	 * */
	
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	/*
	 * @RequestMapping(value="login.me") //RequestMappin 타입의 이노테이션을 붙여서  HandlerMappin 등록
	 * ()안에 속서을 여러개 넣을거면 속성명=값,속성명=값 형태로 작성 매핑값 1개만 쓸거라면 값만써도 됨
	 * @RequestMapping("login.me")
	 * 
	 * 
	 * Spring S
	 * 
	 * 1.HttpServletRequest을 이용해서 전달받기(기존 jsp/servlet 방식)
	 * 해당 메소드의 매개변수로 HttpServletRequest를 작성해 놓으면
	 * 스피링 컨테이너가 해당 메소드를 호출할 때(실행) 자동으로 해당 객체를 생성해서 매개 변수로 주입해준다.
	 * */
	/*
	@RequestMapping(value="login,me")
	public String loginMember(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.println(userId);
		System.out.println(userPwd);
		
		return "main";
		
	}*/
	
	/*
	 * 2. @RequestParam 어노테이션을 이용하는 방법
	 * 	request.getParameter("키")로 값을 뽑는 역할을 대신 수행해주는 어노테이션
	 * value 속성의 value로 jsp에서 작성했던 name값을 입력해주면 알아서 매개변수로 담아온다.
	 * 만약 넘어온 값이 비어있다면 defaultValue로 기본값 설정도 가능
	 * 
	 * 
	 * */
	/*
	@RequestMapping("login.me")
	public String loginMember(@RequestParam(value="userId") String userId,
							  @RequestParam(value="userPwd") String userPwd) {
		
		
	
		System.out.println(userId);
		System.out.println(userPwd);
		
		return "main";
		
	} */
	
	/*
	 * 3.@RequestParam 어노테이션을 생략하는 방법
	 * 단 매개변수 명을 jsp의 name 속성값 (요청시 전달한 키값) 과 동일하게 세팅해줘야한다.
	 * 또한 위에서 사용했던 defaultValue
	 * 
	 * */
	/*
	@RequestMapping("login.me")
	public String loginMember(String userId,String userPwd) {
		
		System.out.println(userId);
		System.out.println(userPwd);
		
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		//service에 멤버객체 m을 보내면서 조회
		
		return "main";
		
	}
	*/
	
	/*
	 * 4. 커맨드 객체 방식
	 * 해당 메소드의 매개변수로
	 * 요청시 전달값을 담고자하는 VO 클래스 타입으로 세팅하고
	 * 요청시 전달값의 키값(jsp에서 넘겨주는 name)을 VO 클래스에 담고자 하는 필드명으로 작성
	 * 
	 * 스프링 컨테이너가 해당 객체를 기본생성자로 생성 후 내부적으로 setter 메소드를 찾아서
	 * 요청시 전달한 값을 필드에 담아준다.
	 *
	 * 주의) name값과 담고자하는 필드명이 같아야함
	 * */
	
	/*
	 * 1. 스프링에서 제공하는 Model 객체를 이용하는 방법
	 * 포워딩할 응답뷰로 전달하고자 하는 데이터를 맵형식 (key-value) 으로 담을 수 있는 영역
	 * 단 setAttribue() 가 인
	 * 
	 * */
//	
//	@RequestMapping("login.me")
//	public String loginMember(Member m,HttpSession session,Model model) {
//		
//		
//		Member loginUser = memberService.loginMember(m);
//		
//		if(loginUser == null) {
//			//에러페이지에다가 에러메시지 setAttribute
//			//request.setAttribute("errorMsg","로그인실패")
//			//request.getRequestDispatcher("").forward(req,resp);
//			model.addAttribute("errorMsg","로그인 실패");
//			//forward
//			//포워딩 방식(파일 경로를 포함한 파일명을 제시)
//			//servlet-context.xml 의 주소 자동완성 도구 : view resolver
//			//-접두어 : /WEB-INF/views/
//			// 중간 : 파일명
//			// -접미어 : .jsp
//			return "common/errorPage";
//		}else {
//			session.setAttribute("loginUser", loginUser);
//			//redirect 
//			return "redirect:/";
//		}
//		
//	}
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		
		session.removeAttribute("loginUser");
		
		return "redirect:/";
	}
	
	/*
	 * 
	 * 2. 스프링에서 제공하는 ModelAndView 객체를 이용하는 방법
	 * Model은 데이터를 key-value 세트로 담을 수 있다면
	 * View는 응답뷰에 대한 정보를 담을 수 있다.
	 * 이 경우에는 리턴 타입(반환형)이 String이 아닌 modelAndView 형태로 return 해야한다.
	 * 
	 * Model과 View가 결합된 형태의 객체.
	 * Model은 단독 사용 가능하지만 View는 불가능하다.
	 * */
	
	//암호화 작업 전
	
	
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m
							,HttpSession session
							,ModelAndView mv) {
		
		/*
		Member loginUser = memberService.loginMember(m);
		
		if(loginUser == null) {
			//model.addAttribute("errorMsg","로그인 실패");
			mv.addObject("errorMsg","로그인 실패");
			
			mv.setViewName("common/errorPage");
		}else {
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		}
		return mv;
		
	}*/
	
	//암호화 작업 후
	//기존에 평무이 디비에 등록되어 있었기 때문에 아이디랑 비밀번호를 같이 입력받아 조회하는 형태로 작업 했었음
	//암호화 작업을 하면 입력받은 비밀번호는 평문이지만 디비에 등록되어있는 비밀번호는 암호문이기 때문에
	//비교시 무조건 다르게 됨
	//아이디로 먼저 회원정보 조회 후 회원이 있으면 비밀번호 암호문 비교 메소드를 이용해서 일치하는지 확인
	
	Member loginUser = memberService.loginMember(m);
	//loginUser = 아이디로만 조회된 회원정보
	//loginUser의 userPwd : 담겨져 있는 비밀번호는 암호화된 비밀번호
	//m.getUserPwd() 암호화 안된 비밀번호 (request받은)
	
	//BcryptPasswordEncoder 객체의 matches 메소드를 사용
	//matches(평문,암호문)을 작성하면 내부적으로 복호화
	//boolean 자료형으로 반납(일치하면 true /아니면 false)
	
	if(loginUser != null && bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
		//하이디로만 조회해온 로그인 유저가 있고(아이디가 있다) 입력받은 평문과 DB에 있은 암호문이 일치하면
		//로그인 성공
		session.setAttribute("loginUser", loginUser);
		mv.setViewName("redirect:/");
	}else {
		//로그인 실패
		mv.addObject("errorMsg","로그인 실패");
		mv.setViewName("common/errorPage");
	}
		return mv;
	}
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		
		return "member/memberEnrollForm";
	}	
	@RequestMapping("insert.me")
	public String insertMember(Member m,HttpSession session,Model model) {			
		  /*
	       * 1. 한글 깨짐 문제 -> web.xml에 스프링에서 제공하는 인코딩 필터를 등록
	       * 2. 나이를 입력하지 않았을 때 int 자료형에 빈 문자열이 들어갈 수 없어서 400 오류
	       *       -> Member.vo age 필드 String으로 변경 (오라클엔 Number 자동형변환 진행되어 상관 없음)
	       * 3. 비밀번호가 사용자가 입력한 그대로(평문)이기 때문에 발생하는 보안문제
	       * 	->Bcrypt 방식의 암호화를 통해서 pwd를 암호문으로 변경
	       * 	1)spring security 모듈에서 제공하는 라이브러리 3개 pom.xml에 등록
	       * 	2)BCryptPasswordEncoder 클래스를 xml 파일에 bean 등록
	       * 	3)web.xml에서 로딩할수 있게 작성
	       */		
		System.out.println("암호화 전 평문 :" +m.getUserPwd());
		
		//암호화 작업
		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		
		m.setUserPwd(encPwd);
		
		int result = memberService.insertMember(m);
		
		if(result>0) {
			session.setAttribute("alertMsg","회원가입성공");
			return "redirect:/";
		}
		else {
			model.addAttribute("errorMsg","회원가입실패");
			return "common/errorPage";
		}
	}
	@RequestMapping("myPage.me")
	public String myPage() {
		
		return "member/myPage";
	}
	@RequestMapping("update.me")
	public String updateMember(Member m,HttpSession session,Model model) {
		
		int result = memberService.updateMember(m);
		
		if(result>0) {
			
			Member updatemem = memberService.loginMember(m);
			
			session.setAttribute("loginUser", updatemem);
			
			session.setAttribute("alertMsg","회원정보변경성공");
			return "redirect:myPage.me";
		}
		else {
			model.addAttribute("errorMsg","회원가입실패");
			return "member/myPage";
		}
		
	}
	@RequestMapping("delete.me")
	public String deleteMember(String userPwd,HttpSession session,Model model) {
		
		Member m = (Member)session.getAttribute("loginUser");
		
		if(userPwd != null && bcryptPasswordEncoder.matches(userPwd, m.getUserPwd())) {
		
		int result = memberService.deleteMember(m.getUserId());
		System.out.println(result);
		
		if(result>0) {
			session.removeAttribute("loginUser");
			session.setAttribute("alertMsg","탈퇴성공");
			return "redirect:";
		}
		else {
			model.addAttribute("errorMsg","회원탈퇴실패");
			return "common/errorPage";
		}
		}else{
			return "redirect:";
		}
	}
}
