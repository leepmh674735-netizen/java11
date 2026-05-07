package com.winter.yubin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder; // 추가
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.yubin.file.FileManager;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    
    @Autowired
    private FileManager fileManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder; // 수정: 주입 및 변수명 완성
    
    @Value("${yubin.member}")
    private String name;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUsername(username);
        
        try {
            MemberDTO result = memberMapper.detail(memberDTO);
            if (result == null) {
                throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
            }
            // MemberDTO는 UserDetails를 implements 하고 있어야 합니다.
            return result; 
        } catch (Exception e) {
            throw new UsernameNotFoundException("데이터베이스 에러 발생", e);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public MemberDTO idCheck(MemberDTO memberDTO) throws Exception {
        return memberMapper.detail(memberDTO);
    }

    public boolean doubleCheck(MemberDTO memberDTO, BindingResult bindingResult) throws Exception {
        boolean hasError = bindingResult.hasErrors();
        
        // 1. 비밀번호 일치 검증
        if (memberDTO.getPassword() != null && !memberDTO.getPassword().equals(memberDTO.getPasswordCheck())) {
            bindingResult.rejectValue("passwordCheck", "member.passwordCheck.notEqual");
            hasError = true;
        }
        
        // 2. ID 중복 검사
        MemberDTO existingMember = memberMapper.detail(memberDTO);
        if (existingMember != null) {
            bindingResult.rejectValue("username", "member.username.equal");
            hasError = true;
        }
        
        return hasError;
    }

    @Override
    public int join(MemberDTO memberDTO, MultipartFile file) throws Exception {
        // 1. 비밀번호 암호화 후 세팅
        String encodedPw = passwordEncoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encodedPw);
        
        // 2. 회원 정보 저장
        int result = memberMapper.join(memberDTO);
        
        // 3. 파일 저장 로직
        if (result > 0 && file != null && !file.isEmpty()) {
            String fileName = fileManager.fileSave(name, file);
            
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setFileName(fileName);
            profileDTO.setOriName(file.getOriginalFilename());
            profileDTO.setUsername(memberDTO.getUsername());
            
            result = memberMapper.addProfile(profileDTO);
        }
        
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDTO detail(MemberDTO memberDTO) throws Exception {
        MemberDTO check = memberMapper.detail(memberDTO);
        
        // BCryptPasswordEncoder를 사용할 경우 matches 메서드로 비교해야 합니다.
        if (check != null && memberDTO.getPassword() != null) {
            if (passwordEncoder.matches(memberDTO.getPassword(), check.getPassword())) {
                return check;
            }
        }
        return null;
    }
}