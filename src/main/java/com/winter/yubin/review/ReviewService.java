package com.winter.yubin.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	public List<ReviewDTO> list (ReviewDTO reviewDTO)throws Exception{
		return reviewMapper.list(reviewDTO);
	}

}