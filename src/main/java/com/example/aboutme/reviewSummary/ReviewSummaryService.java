package com.example.aboutme.reviewSummary;

import com.example.aboutme._core.config.OpenAIConfig;
import com.example.aboutme._core.utils.ReviewSummaryCacheUtil;
import com.example.aboutme.review.Review;
import com.example.aboutme.review.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewSummaryService {
    private final ReviewRepository reviewRepository;
    private final OpenAIConfig openAIConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ReviewSummaryCacheUtil reviewSummaryCacheUtil;

    public String summarizeReviews(Integer expertId) {
        // 캐시에서 요약된 리뷰 가져오기
        String cachedSummary = reviewSummaryCacheUtil.getSummary(expertId);
        if (cachedSummary != null) {
            log.info("캐시된 리뷰 요약 사용: {}", cachedSummary);
            return cachedSummary;
        }

        // 캐시에 없으면 새로 조회
        Timestamp oneMonthAgo = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
        List<Review> reviews = reviewRepository.findByExpertIdAndCreatedAtAfter(expertId, oneMonthAgo);
        log.info("전문가 ID {}에 대한 1개월 이내 리뷰 조회: {}", expertId, reviews);

        StringBuilder allReviews = new StringBuilder();
        for (Review review : reviews) {
            allReviews.append(review.getContent()).append("\n");
        }

        String reviewsContent = allReviews.toString();
        log.info("모든 리뷰 내용 결합: {}", reviewsContent);
        String summary = getSummaryFromOpenAI(reviewsContent);

        // 캐시에 요약된 리뷰 저장
        reviewSummaryCacheUtil.putSummary(expertId, summary);

        return summary;
    }

    private String getSummaryFromOpenAI(String reviews) {
        String apiKey = openAIConfig.getApiKey();
        String apiUrl = openAIConfig.getApiUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "내담자가 상담을 통해 리뷰를 남긴걸 줄테니 리뷰를 요약해서 줘야해 다른 사람이 보고 상담을 하고 싶은 느낌을 주게 요약해줘 1. 부정적인 의견을 빼줘, 2. 칭찬에 대한 내용만 요약해줘 3.최소 400자 ~ 500자로 만들어줘 4. 내용은 반복되지 않고 창의적으로 작성해줘 5. 답변에 상담이 기대에 미치지 못했던 측면을 제외하고, 상담을 추천한다는 긍정적인 의견을 강조하여 요약했습니다. 이런거 넣지마 6.내용이 이어지게 만들어줘 7.따뜻하고 부드러운 말투로 작성해줘" +
                "예시) 다양한 문제를 다각도로 접근하고 공감하며 해결책을 제시하는 전문가. 홍길동 전문가는 깊이 있는 공감과 경청으로 감정을 잘 이해하고 조언해 주는 전문가입니다. 홍길동 전문가는 이야기를 귀 기울여 듣고 진심으로 깊이 공감해 주는 전문가예요. 마키님들이 간단하게 이야기한 내용에서도 감정을 잘 짚어내며 디테일한 부분에까지 이끌어가세요. 상태를 이해하는 데 도움이 되는 조언도 많이 해주시고, 문제점을 스스로 깨달을 수 있도록 인사이트를 주시는 점이 특히 인상적이에요. 긴 상담일지도 모안 마키님들을 잘 파헤쳐서 맞춤형 상담을 진행해 주시고, 해결되지 않는 감정에 대한 디테일한 분석도 알려주셔서 많은 도움이 되었다고 해요. 또한, 같은 신앙을 가진 마키님들에게는 더 편하게 말할 수 있는 점이 좋았다고 해요. 홍길동 전문기사의 상담에서 마키님들은 자신을 사랑하는 법을 배우고, 진심으로 걱정해주는 조언을 받으며 마음의 안정감을 느낄 수 있었다고 합니다. ");

        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", reviews);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(systemMessage, userMessage));
        requestBody.put("temperature", 0.5);

        log.info(" 요청 바디 : {}", requestBody);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
            OpenAIRespDTO openAIResponse = objectMapper.readValue(response.getBody(), OpenAIRespDTO.class);
            String reviewSummary = openAIResponse.choices().get(0).message().content();

            return reviewSummary;
        } catch (HttpClientErrorException e) {
            log.error("OpenAI API 요청 실패 - 상태 코드: {}, 응답 본문: {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            log.error("OpenAI API 요청 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
