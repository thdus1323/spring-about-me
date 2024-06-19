package com.example.aboutme.comm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommService {
    private final CommRepository commRepository;
    private final CommNativeRepository commNativeRepository;

    public List<CommResponse.ClientMainCommListDTO> getMainComms() {

        return commRepository.findCommsWithReply();
    }

    //상품 상세보기
    public CommResponse.CommDetailDTO getCommDetail(int commId) {
        Comm comm = commNativeRepository.findById(commId);
        return new CommResponse.CommDetailDTO(comm);
    }
}
