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
        systemMessage.put("content", "내담자가 상담을 통해 리뷰를 남긴걸 줄테니 리뷰를 요약해서 줘야해...");

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
