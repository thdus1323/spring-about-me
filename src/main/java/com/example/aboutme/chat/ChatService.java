package com.example.aboutme.chat;

import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.counsel.CounselRepository;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import com.example.aboutme.user.UserResponse;
import com.example.aboutme.user.UserService;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final CounselRepository counselRepository;


    @Transactional
    public Chat saveChatMessage(Integer userId, String content, Integer counselId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));

        Counsel counsel = counselRepository.findById(counselId)
                .orElseThrow(() -> new RuntimeException("해당 이용권을 찾을 수 없습니다."));

        Chat chat = Chat.builder()
                .user(user)
                .content(content)
                .counsel(counsel)
                .build();

        return chatRepository.save(chat);
    }

}