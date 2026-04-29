package com.winter.yubin.board.qua;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.yubin.board.BoardMapper;
import com.winter.yubin.file.FileDTO;

@Mapper
public interface QnaMapper extends BoardMapper {
	
	public int fileDelete(FileDTO fileDTO)throws Exception;
	
	public int fileDeleteFor(List<FileDTO> list)throws Exception;

}