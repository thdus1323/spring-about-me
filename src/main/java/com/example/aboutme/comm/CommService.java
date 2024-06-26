package com.example.aboutme.comm;

import com.example.aboutme.comm.ResourceNotFoundException.ResourceNotFoundException;
import com.example.aboutme.reply.Reply;
import com.example.aboutme.user.enums.UserRole;
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

    // 모든 글,댓글 가져오기
    @Transactional
    public List<CommResponse.ALLCommWithRepliesDTO> findAllCommWithReply() {
        return commRepository.findAllCommWithReplies();
    }

    // 아이디로 검색해서 글,댓글 정보 받아오기
    @Transactional
    public CommResponse.CommWithRepliesDTO findByIdDetail(Integer id) {
        CommResponse.CommWithRepliesDTO comm = commRepository.findByIdDetail(id);
        comm.calculateTimeAgo();
        return comm;
    }

    // 전문답변이 있는지 확인
    @Transactional(readOnly = true)
    public boolean hasExpertReply(Integer id) {
        Optional<Comm> optionalComm = commRepository.findById(id);
        return optionalComm.map(comm -> comm.getReplies().stream()
                        .anyMatch(reply -> reply.getUser().getUserRole() == UserRole.EXPERT))
                .orElse(false);
    }

    @Transactional
    public Comm findById(Integer id) {
        Optional<Comm> commOptional = commRepository.findById(id);
        return commOptional.orElse(null); // orElse(null)을 사용하여 엔티티가 없을 경우 null 반환
    }
}
