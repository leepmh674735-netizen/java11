package com.winter.yubin.board;

import java.util.List;

import com.winter.yubin.file.FileDTO;
import com.winter.yubin.pager.Pager;

public interface BoardMapper {
	
	//CRUD
	public Long getCount(Pager pager)throws Exception;
	//R-read
	//List
	public List<BoardDTO> list(Pager pager)throws Exception;
	
	public BoardDTO detail(BoardDTO boardDTO)throws Exception;
	
	//C - create
	public int create(BoardDTO boardDTO)throws Exception;
	
	public int createFile(FileDTO fileDTO)throws Exception;
	
	//U - update
	public int update(BoardDTO boardDTO)throws Exception;
	
	//D - delete
	public int delete(BoardDTO boardDTO)throws Exception;
	
	public FileDTO fileDetail(FileDTO fileDTO)throws Exception;

}