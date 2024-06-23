package com.example.aboutme.comm;

import com.example.aboutme.comm.ResourceNotFoundException.ResourceNotFoundException;
import com.example.aboutme.reply.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CommService {
    private final CommRepository commRepository;
//    private final CommNativeRepository commNativeRepository;


    public List<CommResponse.CommAndReplyDTO> findAllCommsWithReply() {
        return commRepository.findAllCommsWithReply();
    }

    @Transactional
    public CommResponse.CommDetailDTO getCommDetail(int id) {
        Comm comm = commRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comm not found with id " + id));

        List<Comm> comms = commRepository.findByCategory(comm.getCategory());
        System.out.println("comms = " + comms);

        String userName = comm.getUser().getName();
        String clientImage = comm.getUser().getProfileImage();
        List<String> replyContents = comm.getReplies().stream()
                .map(Reply::getContent)
                .collect(Collectors.toList());

        Map<String, List<CommResponse.CommDTO>> commsByCategory = comms.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getCategory().getKorean(),
                        Collectors.mapping(c -> new CommResponse.CommDTO(c.getId(), c.getContent(), c.getTitle(), c.getCategory().getKorean(), c.getCreatedAt()), Collectors.toList())
                ));

        return new CommResponse.CommDetailDTO(
                comm.getId(),
                clientImage,
                userName,
                comm.getContent(),
                comm.getTitle(),
                comm.getCategory().getKorean(),
                comm.getCreatedAt(),
                replyContents,
                commsByCategory
        );
    }

    public Comm findById(Integer id) {
        Optional<Comm> commOptional = commRepository.findById(id);
        return commOptional.orElse(null); // orElse(null)을 사용하여 엔티티가 없을 경우 null 반환
    }
}
