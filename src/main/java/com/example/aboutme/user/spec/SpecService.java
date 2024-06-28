package com.example.aboutme.user.spec;

import com.example.aboutme._core.error.exception.Exception401;
import com.example.aboutme._core.error.exception.Exception403;
import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import com.example.aboutme.user.UserRequestRecord.ExpertSpecUpdateReqDTO;
import com.example.aboutme.user.enums.SpecType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecService {

    private final SpecRepository specRepository;
    private final UserRepository userRepository;


    @Transactional
    public void saveSpecs(ExpertSpecUpdateReqDTO expertSpecUpdateReqDTO, SessionUser sessionUser) {

        // 0. 인증처리

        if (sessionUser == null){
            throw new Exception401("인증되지 않은 유저입니다.");
        }

        // 1. 저장

        List<ExpertSpecUpdateReqDTO.CareerReqDTO> careerList = expertSpecUpdateReqDTO.career();
        if (careerList != null && !careerList.isEmpty()) {
            for (ExpertSpecUpdateReqDTO.CareerReqDTO career : careerList) {
                Spec spec = new Spec();
                User user = userRepository.findById(career.userId()).orElseThrow(() -> {
                    throw new Exception404("해당 유저를 찾지못했습니다.");
                });
                spec.setUser(user);
                spec.setSpecType(SpecType.valueOf(career.specType()));
                spec.setStartYear(career.startYear().toString());
                spec.setEndYear(career.endYear().toString());
                spec.setDetails(career.details());
                specRepository.save(spec);
            }
        }

        // 학력 데이터 저장
        List<ExpertSpecUpdateReqDTO.EducationReqDTO> educationList = expertSpecUpdateReqDTO.education();
        if (educationList != null && !educationList.isEmpty()) {
            for (ExpertSpecUpdateReqDTO.EducationReqDTO education : educationList) {
                Spec spec = new Spec();
                User user = userRepository.findById(education.userId()).orElseThrow(() -> {
                    throw new Exception404("해당 유저를 찾지못했습니다.");
                });
                spec.setUser(user);
                spec.setSpecType(SpecType.valueOf(education.specType()));
                spec.setStartYear(education.startYear().toString());
                spec.setEndYear(education.endYear().toString());
                spec.setDetails(education.details());
                specRepository.save(spec);
            }
        }
    }
}
