package com.winter.yubin.board.notice;

import java.beans.Transient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.yubin.board.BoardDTO;
import com.winter.yubin.board.BoardService;
import com.winter.yubin.file.FileDTO;
import com.winter.yubin.file.FileManager;
import com.winter.yubin.pager.Pager;

@Service
@Transactional(rollbackFor = Exception.class )
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${yubin.board.notice}")
	private String name;
	

	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		
		pager.makePageNum(noticeMapper.getCount(pager));
		pager.makeStartNum();
		
		return noticeMapper.list(pager);
	}

	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.detail(boardDTO);
	}

	
	@Transient
	@Override
	public int create(BoardDTO boardDTO, MultipartFile [] attach) throws Exception {
		// 1. 게시판 테이블에 글을 추가
		int result = noticeMapper.create(boardDTO);
		
		if(attach==null) {
			return result;
		}
		
		// 2. 파일을 HDD에저장
		for(MultipartFile f: attach) {
			if(f.isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.fileSave(name, f);
			// 3. 파일의 정보들을 DB에 저장
			NoticeFileDTO noticeFileDTO = new NoticeFileDTO();
			noticeFileDTO.setBoardNum(boardDTO.getBoardNum());
			noticeFileDTO.setOriName(f.getOriginalFilename());
			noticeFileDTO.setFileName(fileName);
			
			result = noticeMapper.createFile(noticeFileDTO);
		}
		
		
		
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO, MultipartFile [] attach) throws Exception {
		int result = noticeMapper.update(boardDTO);
		return result;
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		//파일명 조회
		boardDTO = noticeMapper.detail(boardDTO);
		
		//HDD에서 파일 삭제
		for(FileDTO fileDTO:boardDTO.getList()) {
			fileManager.fileDelete(name, fileDTO);
		}
		
		//DB에서 삭제
		int result = noticeMapper.delete(boardDTO);
		return result;
	}
	
	@Override
	public FileDTO fileDetail(FileDTO fileDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.fileDetail(fileDTO);
	}
	
	

}