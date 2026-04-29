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
import com.winter.yubin.pager.Pager;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${yubin.board.qna}")
	private String name;
	
	// 모든 메서드 호출 전 "name"이라는 키로 값을 모델에 담음
	@ModelAttribute("name")
	public String getName() {
		return this.name;
	}
	
	// 1. 리스트 조회
	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		List<BoardDTO> ar = qnaService.list(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager); // 페이징 처리를 위해 pager도 전달하는 것이 좋음
		return "board/list";
	}
	
	// 2. 상세 조회
	@GetMapping("detail")
	public String detail(QnaDTO qnaDTO, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.detail(qnaDTO);
		
		if(boardDTO != null) {
			model.addAttribute("dto", boardDTO);
			return "board/detail";
		} else {
			// 데이터가 없을 경우 에러 메시지와 함께 리스트로 리다이렉트 처리 로직 추가 가능
			return "redirect:./list";
		}
	}
	
	// 3. 등록 페이지 이동
	@GetMapping("create")
	public String create() throws Exception {
		return "board/create";
	}

	// 4. DB 등록 실행
	@PostMapping("create")
	public String create(QnaDTO qnaDTO, @RequestParam(value="attach", required=false) MultipartFile[] attach) throws Exception {
		int result = qnaService.create(qnaDTO, attach);
		
		// result값에 따른 성공/실패 처리를 alert창 등으로 구현하면 더 좋음
		return "redirect:./list";
	}
	
	// 5. 수정 페이지 이동
	@GetMapping("update")
	public String update(QnaDTO qnaDTO, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.detail(qnaDTO);
		model.addAttribute("dto", boardDTO);
		return "board/update";
	}
	
	// 6. DB 수정 실행
	@PostMapping("update")
	public String update(QnaDTO qnaDTO, @RequestParam(value="attach", required=false) MultipartFile[] attach) throws Exception {
		int result = qnaService.update(qnaDTO, attach);
		
		return "redirect:./list";
	}
	
	// 7. DB 삭제 실행
	// Delete는 보통 PostMapping이나 DeleteMapping을 사용합니다.
	@PostMapping("delete")
	public String delete(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.delete(qnaDTO);
		return "redirect:./list";
	}

}