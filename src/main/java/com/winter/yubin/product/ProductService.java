package com.winter.yubin.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.yubin.file.FileManager;
import com.winter.yubin.pager.Pager;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FileManager fileManager;
	
	
	@Value("${app.product}")
	private String name;
	
	public List<ProductDTO> list(Pager pager)throws Exception{
		pager.makePageNum(productMapper.getCount(pager));
		pager.makeStartNum();
		
		return productMapper.list(pager);
		
	}
	
	public ProductDTO detail(ProductDTO productDTO)throws Exception{
		return productMapper.detail(productDTO);
	}
	
	public int create(ProductDTO productDTO, MultipartFile file)throws Exception{
		int result = productMapper.create(productDTO);
		
		if(file != null && !file.isEmpty()) {
			String fileName = fileManager.fileSave(name, file);
			ProductFileDTO productFileDTO = new ProductFileDTO();
			productFileDTO.setFileName(fileName);
			productFileDTO.setOriName(file.getOriginalFilename());
			productFileDTO.setProductNum(productDTO.getProductNum());
			productMapper.createFile(productFileDTO);
		}
		
		return result;
		
	}
	
	
}