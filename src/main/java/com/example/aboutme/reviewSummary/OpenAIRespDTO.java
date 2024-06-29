package com.example.aboutme.reviewSummary;

import java.util.List;

public record OpenAIRespDTO(
        String id,
        String object,
        long created,
        String model,
        List<Choice> choices,
        Usage usage,
        String system_fingerprint
) {
    public record Choice(
            int index,
            Message message,
            Object logprobs,
            String finish_reason
    ) {
    }

    public record Message(
            String role,
            String content
    ) {
    }

    public record Usage(
            int prompt_tokens,
            int completion_tokens,
            int total_tokens
    ) {
    }
}
