package com.winter.yubin.review;

import java.util.List;

import com.winter.yubin.board.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final NoticeService noticeService;
	
	@Autowired
	private ReviewMapper reviewMapper;

    ReviewService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
	
	public List<ReviewDTO> list (ReviewDTO reviewDTO)throws Exception{
		return reviewMapper.list(reviewDTO);
	}
	
	public int create(ReviewDTO reviewDTO)throws Exception{
		return reviewMapper.create(reviewDTO);
	}
	
	public int delete(ReviewDTO reviewDTO)throws Exception{
		return reviewMapper.delete(reviewDTO);
		 
	}
	
	public int update(ReviewDTO reviewDTO)throws Exception{
		return reviewMapper.update(reviewDTO);
	}

}