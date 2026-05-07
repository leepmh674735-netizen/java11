package com.winter.yubin.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.winter.yubin.board.BoardDTO;
import com.winter.yubin.file.FileDTO;
import com.winter.yubin.member.MemberDTO;
import com.winter.yubin.pager.Pager;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice/*")
@CrossOrigin("*")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Value("${yubin.board.notice}")
	private String name;
	
	@ModelAttribute("name")
	public String getName() {
		return this.name;
	}
	
	@GetMapping("down")
	public String fileDown(NoticeFileDTO noticeFileDTO, Model model)throws Exception{
		
		FileDTO fileDTO = noticeService.fileDetail(noticeFileDTO);
		
		model.addAttribute("fileDTO", fileDTO);
		
		return "fileDownView";
	}
	
	@GetMapping("list")
	public String list(Pager pager, Model model)throws Exception{
		
		List<BoardDTO> ar = noticeService.list(pager);
		
		model.addAttribute("list", ar);
		
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(NoticeDTO noticeDTO, Model model)throws Exception{
		BoardDTO boardDTO = noticeService.detail(noticeDTO);
		if(boardDTO != null) {
			model.addAttribute("dto", boardDTO);
			return "board/detail";
			
		}else {
			model.addAttribute("result", "없는 글이다");
			model.addAttribute("url", "./list");
			return "commons/result";
		}
		
	}
	
	@GetMapping("create")
	public String create()throws Exception{
		return "board/form-";
	}

	@PostMapping("create")
	public String create(NoticeDTO noticeDTO, @RequestParam(value="attach", required = false) MultipartFile [] attach, Model model)throws Exception{
		int result = noticeService.create(noticeDTO, attach);
		if(result>0) {
			model.addAttribute("result", "글 등록 성공");
			model.addAttribute("url", "./list");
		}
		
		
		return "commons/result";
	}
	
	@GetMapping("update")
	public String update(NoticeDTO noticeDTO, Model model)throws Exception{
		BoardDTO boardDTO = noticeService.detail(noticeDTO);
		model.addAttribute("dto", boardDTO);
		return "board/update";
	}
	
	@PostMapping("update")
	public ModelAndView update(NoticeDTO noticeDTO, @RequestParam("attach") MultipartFile [] attach, Model model)throws Exception{
		int result = noticeService.update(noticeDTO, attach);
		
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:./list");
		
		mv.addObject("dto", noticeDTO);
		
		return mv;
	}
	
	@PostMapping("delete")
	public String delete(NoticeDTO noticeDTO, HttpSession session, Model model)throws Exception{
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		BoardDTO boardDTO = noticeService.detail(noticeDTO);
		if(boardDTO.getBoardWriter().equals(memberDTO.getUsername())) {
			int result = noticeService.delete(noticeDTO);
			return "redirect:./list";
			
		}else {
			model.addAttribute("result", "작성자가 아님");
			model.addAttribute("url", "./list");
			return "commons/result";
		}
		
		
	}
	

}