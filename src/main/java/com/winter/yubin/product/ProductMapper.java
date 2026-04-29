package com.winter.yubin.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.yubin.pager.Pager;

@Mapper
public interface ProductMapper {
	
	public Long getCount(Pager pager)throws Exception;
	
	public List<ProductDTO> list(Pager pager)throws Exception;
	
	public ProductDTO detail(ProductDTO productDTO)throws Exception;
	
	public int create(ProductDTO productDTO)throws Exception;
	
	public int createFile(ProductFileDTO productFileDTO)throws Exception;

}