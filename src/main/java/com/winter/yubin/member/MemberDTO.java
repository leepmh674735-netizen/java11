package com.winter.yubin.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class MemberDTO implements UserDetails {
    
    // Spring Security 상태 필드
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @NotBlank(groups = GroupAdd.class, message = "ID는 필수입니다")
    private String username;
    
    @NotBlank(groups = {GroupAdd.class, GroupUpdate.class}, message = "이름은 필수입니다")
    private String name;
    
    @Size(groups = GroupAdd.class, max = 15, min = 4, message = "비밀번호는 4자 이상 15자 이하로 입력해주세요")
    @NotBlank(groups = GroupAdd.class)
    private String password;
    
    private String passwordCheck;
    
    private String phone;
    
    @Email(groups = {GroupAdd.class, GroupUpdate.class}, message = "이메일 형식이 올바르지 않습니다")
    private String email;
    
    @Past(groups = {GroupAdd.class, GroupUpdate.class}, message = "생년월일은 과거 날짜여야 합니다")
    private LocalDate birth;
    
    private ProfileDTO profileDTO;
    
    // 권한 정보를 담는 리스트
    private List<RoleDTO> roles;

    // --- UserDetails 필수 구현 메서드 ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        // roles 리스트가 null이 아닌지 확인 후 변환
        if (this.roles != null) {
            for (RoleDTO roleDTO : this.roles) {
                // SimpleGrantedAuthority에 권한 이름(예: ROLE_USER)을 담아 리스트에 추가
                authorities.add(new SimpleGrantedAuthority(roleDTO.getRoleName()));
            }
        }
        
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}