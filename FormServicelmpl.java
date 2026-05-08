package com.springinpractice.ch07.service.impl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springinpractice.ch07.dao.ForumDao;
import com.springinpractice.ch07.dao.MessageDao;
import com.springinpractice.ch07.domain.Forum;
import com.springinpractice.ch07.domain.Message;
import com.springinpractice.ch07.service.ForumService;

import lombok.RequiredArgsConstructor; // 롬복 사용 시 생성자 주입이 간편합니다

@Service
@Transactional
@RequiredArgsConstructor // final 필드에 대해 생성자 주입을 자동으로 생성합니다
@PreAuthorize("denyAll") // 기본적으로 모든 메서드 접근 차단 (보안 강화)
public class ForumServiceImpl implements ForumService {

    private final ForumDao forumDao;
    private final MessageDao messageDao;

    @Override
    @PreAuthorize("hasRole('ROLE_PERM_READ_FORUMS')") 
    public List<Forum> getForums() { 
        return forumDao.getAll(); 
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERM_CREATE_MESSAGES')") 
    public void createMessage(Message message) {
        messageDao.create(message);
    }
    
    // other methods...
}
