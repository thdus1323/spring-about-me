package com.example.aboutme.reply;

import com.example.aboutme.comm.Comm;
import com.example.aboutme.comm.CommRepository;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final CommRepository commRepository;
    private final UserRepository userRepository;


    @Transactional
    public void saveExpertReply(ReplyResponse.ReplyDataDTO newReply, Integer userId) {
        Comm comm = commRepository.findById(Integer.valueOf(newReply.getId()))
                .orElseThrow(() -> new RuntimeException("Comm not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Reply reply = new Reply(user, comm, newReply);
        replyRepository.save(reply);
    }

    @Transactional
    public void saveClientReply(ReplyResponse.ClientReplyDataDTO newReply, Integer userId) {
        Comm comm = commRepository.findById(Integer.valueOf(newReply.getId()))
                .orElseThrow(() -> new RuntimeException("Comm not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Reply reply = new Reply(user, comm, newReply);
        replyRepository.save(reply);
    }

    @Transactional
    public void deleteReply(Integer id) {
        replyRepository.deleteById(id);
    }
}
