package com.example.aboutme.comm;

import com.example.aboutme.comm.ResourceNotFoundException.ResourceNotFoundException;
import com.example.aboutme.reply.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CommService {
    private final CommRepository commRepository;
    private final CommNativeRepository commNativeRepository;

    public List<CommResponse.ClientMainCommListDTO> getMainComms() {

        return commRepository.findCommsWithReply();
    }

    //상품 상세보기
//    public CommResponse.CommDetailDTO getCommDetail(int id) {
//        Comm comm = commNativeRepository.findById(id);
//        return new CommResponse.CommDetailDTO(comm);
//    }

    @Transactional
    public CommResponse.CommDetailDTO getCommDetail(int id) {
        Comm comm = commRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comm not found with id " + id));

        String userName = comm.getUser().getName();
        List<String> replyContents = comm.getReplies().stream()
                .map(Reply::getContent)
                .collect(Collectors.toList());

        return new CommResponse.CommDetailDTO(
                comm.getId(),
                userName,
                comm.getContent(),
                comm.getTitle(),
                comm.getCategory(),
                comm.getCreatedAt(),
                replyContents
        );
    }
}
