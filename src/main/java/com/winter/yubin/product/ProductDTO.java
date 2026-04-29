package com.winter.yubin.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	
	private Long productNum;
	private String productName;
	private String productContents;
	private String productType;
	private Double productRate;
	
	private ProductFileDTO productFileDTO;

}