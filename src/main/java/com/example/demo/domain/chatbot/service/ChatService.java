package com.example.demo.domain.chatbot.service;

import com.example.demo.domain.chatbot.GptConfig;
import com.example.demo.domain.chatbot.dto.ImprovePointResponse;
import com.example.demo.domain.chatbot.dto.ImprovementDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ChatService {

    private final OpenAiService openAiService;

    @Autowired
    public ChatService(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    public ImprovePointResponse generate(List<ChatMessage> chatMessages) throws IOException, JsonProcessingException {

        ChatCompletionRequest build = ChatCompletionRequest.builder()
                .messages(chatMessages)
                .maxTokens(GptConfig.MAX_TOKEN)
                .temperature(GptConfig.TEMPERATURE)
                .topP(GptConfig.TOP_P)
                .model(GptConfig.MODEL)
                .build();

        ChatCompletionResult chatCompletionResult = openAiService.createChatCompletion(build);

        String futureResult = chatCompletionResult.getChoices().get(0).getMessage().getContent();
        System.out.println("API Response: " + futureResult);

        ObjectMapper objectMapper = new ObjectMapper();

        if (futureResult.trim().startsWith("{") || futureResult.trim().startsWith("[")) {
            return objectMapper.readValue(futureResult, ImprovePointResponse.class);
        } else {
            ImprovePointResponse response = new ImprovePointResponse();
            ImprovementDetail detail = new ImprovementDetail();

            detail.setType("General Feedback");
            detail.setImprovementPoint(futureResult);
            detail.setAdvice("Please review the above feedback for improvements.");

            response.setImprovementResponse(List.of(detail));

            return response;
        }
    }
}
