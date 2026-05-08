package com.springinpractice.ch04.web;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

// Lombok을 사용하면 getter/setter를 일일이 작성하지 않아도 되어 편리합니다.
@Getter
@Setter
public class AccountForm {
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;
    
    private boolean marketingOk = true;
    private boolean acceptTerms = false; // faulse -> false

    // toString() 메서드 교정
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("username", username)
            .append("firstName", firstName) // lastName -> firstName으로 교정
            .append("lastName", lastName)
            .append("email", email)
            .append("marketingOk", marketingOk)
            .append("acceptTerms", acceptTerms)
            .toString();
    }
}
