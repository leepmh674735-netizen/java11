package com.winter.yubin.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.winter.yubin.board.BoardDTO;
import com.winter.yubin.board.notice.NoticeDTO;
import com.winter.yubin.pager.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${app.board.qna}")
	private String name;
	
	@ModelAttribute("name")
	public String getName() {
		return this.name;
	}
	
	
	@GetMapping("list")
	public String list(Pager pager, Model model)throws Exception{
		
		List<BoardDTO> ar = qnaService.list(pager);
		
		model.addAttribute("list", ar);
		
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(QnaDTO qnaDTO, Model model)throws Exception{
		BoardDTO boardDTO = qnaService.detail(qnaDTO);
		model.addAttribute("dto", boardDTO);
		return "board/detail";
	}
	
	@GetMapping("create")
	public String create()throws Exception{
		return "board/create";
	}

	@PostMapping("create")
	public String create(QnaDTO qnaDTO, @RequestParam("attach") MultipartFile [] attach)throws Exception{
		int result = qnaService.create(qnaDTO, attach);
		
		
		return "redirect:./list";
	}
	
	@GetMapping("update")
	public String update(QnaDTO qnaDTO, Model model)throws Exception{
		BoardDTO boardDTO = qnaService.detail(qnaDTO);
		model.addAttribute("dto", boardDTO);
		return "board/update";
	}
	
	@PostMapping("update")
	public String update(QnaDTO qnaDTO, @RequestParam("attach") MultipartFile [] attach)throws Exception{
		int result = qnaService.update(qnaDTO, attach);
		
		return "redirect:./list";
	}
	
	@PostMapping("delete")
	public String delete(QnaDTO qnaDTO)throws Exception{
		int result = qnaService.delete(qnaDTO);
		return "redirect:./list";
	}

}