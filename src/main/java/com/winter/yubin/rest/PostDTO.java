package com.winter.yubin.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PostDTO {
	private Long userId;
	private Long id;
	private String title;
	private String body;

}
