package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.spring.board.model.service.BoardServiceImpl;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {
	
	@Autowired
	private BoardServiceImpl boardService;
	
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		
		int listCount = boardService.selectListCount();
		
		int pageLimit = 10;
		int boardLimit = 5;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Board> list = boardService.selectList(pi);
		
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		
		return "board/boardListView";
	}
	
	@RequestMapping("enrollForm.bo")
	public String boardEnrollForm() {
		
		return "board/boardEnrollForm";
	}
	
	@RequestMapping("insert.bo")
	public String insertBoard(Board b, MultipartFile upfile, HttpSession session, Model model) {
		
		// 첨부파일 유무 여부에 관계없이 upfile 객체는 생성됨(filename으로 첨부파일 유무 확인)
		
		// 전달된 파일이 있을 경우 -> 파일명 수정 작업 후 서버 업로드 -> 원본명, 서버에 업로드된 경로를 b에 담기
		if(!upfile.getOriginalFilename().equals("")) {
			
			/*
			// 1. 원본 파일명 뽑기
			String originName = upfile.getOriginalFilename();
			
			// 2. 사간 형식을 문자열로 뽑기
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			// 3. 뒤에 붙을 5자리 랜덤 값 뽑기
			int ranNum = (int)(Math.random() * 90000 + 10000);
			
			// 4. 원본 파일명으로부터 확장자 명 뽑기
			String ext = originName.substring(originName.lastIndexOf("."));
			
			// 5. 다 이어붙이기
			String changeName = currentTime + ranNum + ext;
			
			// 6. 업로드 하고자하는 물리적인 위치 알아내기(경로)
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
			
			// 7. 경로와 수정 파일명을 합쳐서 업로드하기
			try {
				upfile.transferTo(new File(savePath + changeName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
			
			String changeName = saveFile(upfile, session);
			
			// 8. 원본명, 서버에 업로드된 경로를 Board b에 담아주기
			b.setOriginName(upfile.getOriginalFilename());
			b.setChangeName("resources/uploadFiles/"+changeName);
		}
			
		// 넘어온 첨부파일이 있을 경우 Board b : 제목, 작성자, 내용, 원본파일명, 파일저장경로
		// 넘어온 첨부파일이 없을 경우 Board b : 제목, 작성자, 내용
		
		int result = boardService.insertBoard(b);
		
		if(result > 0) {
			
			session.setAttribute("alertMsg", "게시글 작성 성공");
			return "redirect:list.bo";
		}
		else {
			
			model.addAttribute("errorMsg", "게시글 작성 실패");
			
			return "common/errorPage";
		}
		
	}
	
	public String saveFile(MultipartFile upfile, HttpSession session) {
		
		// 1. 원본 파일명 뽑기
		String originName = upfile.getOriginalFilename();
		
		// 2. 사간 형식을 문자열로 뽑기
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 3. 뒤에 붙을 5자리 랜덤 값 뽑기
		int ranNum = (int)(Math.random() * 90000 + 10000);
		
		// 4. 원본 파일명으로부터 확장자 명 뽑기
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 5. 다 이어붙이기
		String changeName = currentTime + ranNum + ext;
		
		// 6. 업로드 하고자하는 물리적인 위치 알아내기(경로)
		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
		
		// 7. 경로와 수정 파일명을 합쳐서 업로드하기
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return changeName;
	}
	
	@RequestMapping("detail.bo")
	public String selectBoard(int bno, Model model) {
		
		int result = boardService.increaseCount(bno);
		
		if(result > 0) {
			
			Board b = boardService.selectBoard(bno);
			model.addAttribute("b", b);
			
			return "board/boardDetailView";
		}
		
		else {
			
			model.addAttribute("errorMsg", "상세보기 실패");
			
			return "common/errorPage";
		}
	}
	
	@RequestMapping("delete.bo")
	public ModelAndView deleteBoard(int bno, String filePath, HttpSession session, ModelAndView mv) {
		
		int result = boardService.deleteBoard(bno);
		
		// 첨부 파일이 있었을 경우 - 파일 삭제(new File(실제 경로).delete())
		
		if(filePath!=null) {
			
			new File(session.getServletContext().getRealPath("/"+filePath)).delete();
		}
		
		if(result > 0) {
			
			session.setAttribute("alertMsg", "게시글 삭제 성공");
			mv.setViewName("redirect:list.bo");
		}
		else {
			
			mv.addObject("errorMsg", "게시글 삭제 실패");
			
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	@RequestMapping("updateForm.bo")
	public ModelAndView updateForm(int bno, ModelAndView mv) {
		
		Board b = boardService.selectBoard(bno);
		
		mv.addObject("b", b);
		
		mv.setViewName("board/boardUpdateForm");
		
		return mv;
	}
	
	@RequestMapping("update.bo")
	public ModelAndView updateBoard(Board b, MultipartFile upfile, HttpSession session, ModelAndView mv) {
		
		if(!upfile.getOriginalFilename().equals("")) { // 새로운 첨부 파일이 있을 경우
			
			if(b.getChangeName()!=null) { // 기존 첨부 파일이 있을 경우
				
				new File(session.getServletContext().getRealPath("/"+b.getChangeName())).delete(); // 삭제
				
				String changeName = saveFile(upfile, session); // 새로운 첨부 파일 업로드
				
				b.setOriginName(upfile.getOriginalFilename());
				b.setChangeName("resources/uploadFiles/"+changeName);
			}
		}
		
		int result = boardService.updateBoard(b);
		
		if(result > 0) {
			
			session.setAttribute("alertMsg", "게시글 수정 성공");
			
			mv.setViewName("redirect:detail.bo?bno=" + b.getBoardNo());
		}
		
		else {
			
			mv.addObject("errorMsg", "게시글 수정 실패");
			
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="replyList.bo", produces="application/json; charset=UTF-8")
	public String selectReplyList(int bno) {
		
		ArrayList<Reply> list = boardService.selectReplyList(bno);
		
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="insertReply.bo", produces="html/text; charset=UTF-8")
	public void insertReply(Reply r, HttpServletResponse response) throws IOException {
		
		int result = boardService.insertReply(r);
		
		response.getWriter().print(result);
	}
	
	@ResponseBody
	@RequestMapping(value="topList.bo", produces="application/json; charset=UTF-8")
	public String selectTopList() {
		
		ArrayList<Board> list = boardService.selectTopList();
		
		return new Gson().toJson(list);
	}
}