package com.winter.yubin.member;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDTO {
    
    @NotBlank
    private String username; 
    
    @NotBlank
    private String name;
    
    @Size(max = 10, min = 4)
    private String password; 
    
    private String phone;
    
    @Email
    private String email;
    
    @Past
    private LocalDate birth; 
    
    private ProfileDTO profileDTO;
}