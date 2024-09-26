package com.example.demo.domain.chatbot.controller;

import com.example.demo.domain.chatbot.dto.ImprovePointResponse;
import com.example.demo.domain.chatbot.service.ChatService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/completion")
    public ResponseEntity<ImprovePointResponse> generateCompletion(@RequestBody ChatCompletionRequest request) throws IOException {
        List<ChatMessage> chatMessageList = request.getMessages();

        ImprovePointResponse response = chatService.generate(chatMessageList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload-image")
    public ResponseEntity<ImprovePointResponse> handleImageUpload(@RequestParam("file") MultipartFile file) throws IOException, TesseractException {
        File tempFile = File.createTempFile("upload", file.getOriginalFilename());
        file.transferTo(tempFile);

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("./src/main/resources/tessdata"); // tessdata 경로 설정
        tesseract.setLanguage("kor"); // 언어 설정

        String extractedText = tesseract.doOCR(tempFile);

        ChatCompletionRequest chatRequest = new ChatCompletionRequest();
        chatRequest.setModel("gpt-4");
        ChatMessage message = new ChatMessage("user", "OCR로 추출된 텍스트: " + extractedText + ". 이 정보를 바탕으로 절세 조언을 제공해 주세요.");
        chatRequest.setMessages(List.of(message));

        ImprovePointResponse response = chatService.generate(chatRequest.getMessages());

        tempFile.delete();

        return ResponseEntity.ok(response);
    }


}
