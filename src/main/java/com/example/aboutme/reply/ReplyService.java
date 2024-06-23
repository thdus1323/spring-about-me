package com.example.aboutme.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public Reply saveReply(Reply reply) {
        return replyRepository.save(reply);
    }

}
