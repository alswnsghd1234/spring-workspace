package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("list.bo")
	public String selectList(
			@RequestParam(value="cpage",defaultValue="1") int currentPage,
			Model model) {
		
		
		int listCount = boardService.selectListCount();
		
		int pageLimit = 10;
		int boardLimit = 5;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Board> list = boardService.selectList(pi);
		
		model.addAttribute("list",list);
		model.addAttribute("pi",pi);
		System.out.println(list);
		
		return "board/boardListView";
	}
	
	@RequestMapping("enrollForm.bo")
	public String boardenrollForm() {		
		
		return "board/boardEnrollForm";
	}
	@RequestMapping("insert.bo")
	public String insertBoard(
								Board b
								,MultipartFile upfile
								,HttpSession session
								,Model model) {
		
//		System.out.println(b);
//		System.out.println(upfile);
		//첨부파일을 선택하고 안하고 상관없이 객체는 생성됨(filename 있나 없나로 첨부파일 유무 확인)
		
		//전달된 파일이 있을 경우 -> 파일명 수정 작업 후 서버업로드 -> 원본명,서버에 업로드된 경로를 b에 담기
		
		if(!upfile.getOriginalFilename().equals("")) {
			/*
			//ex)dog.jpg ->  "202206012234134.jpg"
			//1.원본파일명 뽑기
			
			String originName = upfile.getOriginalFilename(); // "dog.jpg"
			
			//2.시간 형식을 문자열로 뽑아오기
			//년월일시분초
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			//3.뒤에 붙을 5자리 랜덤값 뽑기
			int ranNum = (int)(Math.random() *90000 +10000); // 5자리 랜덤값
			
			//4.원본 파일명으로부터 확장자명 뽑기
			//.jpg
			String ext = originName.substring(originName.lastIndexOf("."));
			
			//5.다 이어붙이기
			String changeName = currentTime + ranNum+ext;
			System.out.println(originName);
			System.out.println(changeName);
			
			//6.업로드 하고자하는 물리적인 위치 알아내기(경로)
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
			
			System.out.println(savePath);
			
			//7. 경로와 수정파일명을 합쳐서 업로드하기
			try {
				upfile.transferTo(new File(savePath+changeName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
			 */
		
		String changeName = saveFile(upfile,session);
		
		//8.원본명, 서버에 업로드된 경로를 Board b에 담아주기
		b.setOriginName(upfile.getOriginalFilename());
		b.setChangeName("resources/uploadsFiles/"+changeName);
	}
		//넘어온 첨부파일이 있을 경우 b :제목, 작성자, 내용, 원본파일명, 파일저장경로
		//넘어온 첨부파일이 없을 경우 Board b : 제목, 작성자, 내용
		
		int result = boardService.insertBoard(b);
		
		if(result>0) {
			
			session.setAttribute("alert", "게시글 작성 성공");
			return "redirect:list.bo";
		}else {//실패
			model.addAttribute("errorMsg","게시글 작성 실패");
			return "common/errorPage";
		}
			
		
	}
	//현재 넘어온 첨부파일 자체를 서버 폴더에 저장시키는 역할
	public String saveFile(						
						MultipartFile upfile
						,HttpSession session
						) {

	//1.원본파일명 뽑기
	
	String originName = upfile.getOriginalFilename(); // "dog.jpg"
	
	//2.시간 형식을 문자열로 뽑아오기
	//년월일시분초
	String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	
	//3.뒤에 붙을 5자리 랜덤값 뽑기
	int ranNum = (int)(Math.random() *90000 +10000); // 5자리 랜덤값
	
	//4.원본 파일명으로부터 확장자명 뽑기
	//.jpg
	String ext = originName.substring(originName.lastIndexOf("."));
	
	//5.다 이어붙이기
	String changeName = currentTime + ranNum+ext;
	System.out.println(originName);
	System.out.println(changeName);
	
	//6.업로드 하고자하는 물리적인 위치 알아내기(경로)
	String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
	
	System.out.println(savePath);
	
	//7. 경로와 수정파일명을 합쳐서 업로드하기
	try {
		upfile.transferTo(new File(savePath+changeName));
	} catch (IllegalStateException | IOException e) {
		e.printStackTrace();
	}
	return changeName;
}
	@RequestMapping("detail.bo")
	public String boardDetailView(Board b
			,MultipartFile upfile
			,HttpSession session
			,Model model) {
		
		
		
		
		return "board/boardDetailView";
	}
	
	@RequestMapping("update.bo")
	public String boardUpdate() {
		
		return "board/boardDetailView";
	}
	@RequestMapping("delete.bo")
	public String boardDelete() {
		
		return "board/boardDetailView";
	}
}
