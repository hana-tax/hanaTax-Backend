package com.example.demo.domain.notification.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import net.nurigo.sdk.message.model.Message;

@Service
public class SmsService {
    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    final DefaultMessageService messageService;

    @Value("${spring.sms.sender}")
    private String senderNumber;
    public SmsService(@Value("${spring.sms.api-key}") String apiKey,
                      @Value("${spring.sms.api-secret}") String apiSecret) {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    public void sendMessage(String to, String text) {
        Message message = new Message();
        message.setFrom(senderNumber);
        message.setTo(to);
        message.setText(text);

        try {
            SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
            logger.info("SMS sent successfully: {}", response);
        } catch (Exception e) {
            logger.error("Failed to send SMS: ", e);
        }
    }
}
