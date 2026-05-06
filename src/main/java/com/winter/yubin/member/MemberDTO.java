package com.winter.app.member;

import java.time.LocalDate;

import org.checkerframework.checker.regex.qual.Regex;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class MemberDTO {
	
	@NotBlank(groups = GroupAdd.class, message = "ID는 필수 입니다")
	private String username;
	
	@NotBlank(groups = {GroupAdd.class, GroupUpdate.class})
	private String name;
	
	
	@Size(groups = GroupAdd.class, max = 10, min = 4)
	@NotBlank(groups = GroupAdd.class)
	private String password;
	
	private String passwordCheck;
	
	private String phone;
	
	@Email(groups = {GroupAdd.class, GroupUpdate.class})
	private String email;
	
	@Past(groups = {GroupAdd.class, GroupUpdate.class})
	private LocalDate birth;
	
	private ProfileDTO profileDTO;

}