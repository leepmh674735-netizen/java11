package com.winter.yubin.board.qua;

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
import com.winter.yubin.file.FileDTO;
import com.winter.yubin.pager.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	// application.properties 등에서 설정된 게시판 이름을 가져옴
	@Value("${app.board.qna}")
	private String name;
	
	// 모든 요청 메서드에서 "name"이라는 이름으로 게시판 제목을 사용할 수 있게 함
	@ModelAttribute("name")
	public String getName() {
		return this.name;
	}
	
	// 1. 목록 조회
	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		List<BoardDTO> ar = qnaService.list(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager); // 페이징 정보도 함께 전달하는 것이 좋습니다.
		return "board/list";
	}
	
	// 2. 상세 조회
	@GetMapping("detail")
	public String detail(QnaDTO qnaDTO, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.detail(qnaDTO);
		model.addAttribute("dto", boardDTO);
		return "board/detail";
	}
	
	// 3. 등록 폼 이동
	@GetMapping("create")
	public String create() throws Exception {
		return "board/create";
	}

	// 4. 등록 처리 (파일 첨부 포함)
	@PostMapping("create")
	public String create(QnaDTO qnaDTO, @RequestParam("attach") MultipartFile[] attach) throws Exception {
		int result = qnaService.create(qnaDTO, attach);
		return "redirect:./list";
	}
	
	// 5. 수정 폼 이동
	@GetMapping("update")
	public String update(QnaDTO qnaDTO, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.detail(qnaDTO);
		model.addAttribute("dto", boardDTO);
		return "board/update";
	}
	
	// 6. 수정 처리
	@PostMapping("update")
	public String update(QnaDTO qnaDTO, @RequestParam("attach") MultipartFile[] attach) throws Exception {
		int result = qnaService.update(qnaDTO, attach);
		return "redirect:./list";
	}
	
	// 7. 삭제 처리
	@PostMapping("delete")
	public String delete(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.delete(qnaDTO);
		return "redirect:./list";
	}
	
	// 8. 파일 다운로드
	@GetMapping("down")
	public String fileDown(FileDTO fileDTO, Model model) throws Exception {
		fileDTO = qnaService.fileDetail(fileDTO);
		model.addAttribute("fileDTO", fileDTO);
		// 커스텀 뷰(AbstractView를 상속받은 fileDownView 빈)로 이동
		return "fileDownView";
	}
}