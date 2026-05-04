package com.winter.yubin.member;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class MemberDTO {
	
	private String username;
    private String name;
    private String password;
    private String phone;
    private String email;
    private LocalDate birth;
    
    private ProfileDTO profileDTO;
    
}
