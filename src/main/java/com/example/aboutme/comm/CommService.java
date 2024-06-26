package com.example.aboutme.comm;

import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme.comm.ResourceNotFoundException.ResourceNotFoundException;
import com.example.aboutme.reply.Reply;
import com.example.aboutme.reply.ReplyRepository;
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
    private final ReplyRepository replyRepository;


    @Transactional
    public CommResponse.CommDetailDTO getCommDetail(int id) {
        // 주어진 ID로 게시글을 가져옵니다.
        Comm comm = commRepository.findById(id)
                .orElseThrow(() -> new Exception404("게시물을 찾을 수 없습니다"));

        // 주어진 게시글의 댓글을 가져옵니다.
        List<Reply> replies = replyRepository.findByCommId(comm.getId());

        // 같은 카테고리의 다른 글들과 해당 글들의 댓글을 가져옵니다.
        List<Comm> relatedComms = commRepository.findByCategoryWithRepliesAndExcludeId(comm.getCategory(), comm.getId());

        return new CommResponse.CommDetailDTO(comm, replies, relatedComms);
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

    // 전문답변이 있는지 확인하는 메서드 추가
    @Transactional
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
