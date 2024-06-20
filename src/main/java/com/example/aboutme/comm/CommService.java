package com.example.aboutme.comm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommService {
    private final CommRepository commRepository;



    public List<CommResponse.CommAndReplyDTO> findAllCommsWithReply() {
        return commRepository.findAllCommsWithReply();
    }
}
