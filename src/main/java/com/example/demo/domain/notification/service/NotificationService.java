package com.example.demo.domain.notification.service;

import com.example.demo.domain.mydata.dto.FinancialIncomeDto;
import com.example.demo.domain.mydata.mapper.FinancialIncomeMapper;
import com.example.demo.domain.notification.dto.OverTaxAlertDto;
import com.example.demo.domain.notification.mapper.OverTaxAlertMapper;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.mapper.UserMapper;
import com.example.demo.global.util.ImageUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {
    private final FinancialIncomeMapper financialIncomeMapper;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final SmsService smsService;
    private final OverTaxAlertMapper overTaxAlertMapper;

    @Autowired
    public NotificationService(FinancialIncomeMapper financialIncomeMapper,
                               UserMapper userMapper,
                               EmailService emailService,
                               SmsService smsService,
                               OverTaxAlertMapper overTaxAlertMapper) {
        this.financialIncomeMapper = financialIncomeMapper;
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.smsService = smsService;
        this.overTaxAlertMapper = overTaxAlertMapper;
    }

//        @Scheduled(cron = "0 */1 * * * ?") // 매 1분마다 실행
    @Scheduled(cron = "0 9 * * * ?") // 매일 오전 9시
    public void sendOverTaxAlerts() throws MessagingException {
        // isOverTax가 'Y'인 financial income 목록 조회
        List<FinancialIncomeDto> financialIncomes = financialIncomeMapper.getOverTaxFinancialIncomes();
        System.out.println("financialIncomes: " + financialIncomes);

        for (FinancialIncomeDto income : financialIncomes) {
            // 유저 정보 조회
            User user = userMapper.getUserById(income.getId());
            System.out.println("User: " + user);

            if (user != null) {
                String formattedSum = String.format("%,d", (int) income.getSum());
                String emailSubject = "금융소득 종합과세 대상자 알림";
                String emailText = createEmailText(user.getName(), formattedSum);

                if (user.isAlertMethodEmail()) {
                    emailService.sendEmail(user.getEmail(), emailSubject, emailText);
                    insertOverTaxAlert(user.getId(),1); //이메일
                }

                if (user.isAlertMethodSMS()) {
                    sendSmsAlert(user.getPhoneNumber(), income, formattedSum);
                    insertOverTaxAlert(user.getId(),2); //sms
                }
            }
        }
    }

    private void insertOverTaxAlert(String userId, int alertMethod) {
        OverTaxAlertDto alert = new OverTaxAlertDto();
        alert.setAlertMethod(alertMethod);
        alert.setAlertDate(new Date());
        alert.setAlertState(66); //66: 전송 완료 (정상)
        alert.setId(userId);

        overTaxAlertMapper.insertOverTaxAlert(alert);
    }

    private String createEmailText(String userName, String formattedSum) {
        String imagePath = "C:\\Users\\wldnj\\Downloads\\hana-logo.png";
        String image2Path = "C:\\Users\\wldnj\\Downloads\\hana-white-logo.png";
        String base64Image = ImageUtil.encodeImageToBase64(imagePath);
        String base64Image2 = ImageUtil.encodeImageToBase64(image2Path);

        return "<html>" +
                "<body style='color: #757575; font-size: 13px'>" +
                "<img src='data:image/png;base64," + base64Image + "' alt='Image' style='width:auto; height:40px; max-width:100%;'>" +
                "<div style='background-color: #099A96; color: white; padding: 10px; text-align: left; margin-top: 20px; font-size: 20px; font-weight: bold;'>하나택스 알람메일</div>" +
                "<p><br/><br/>항상 하나택스를 이용해 주시는 고객 여러분께 감사 드립니다.</p>" +
                "<p><br/><br/>" + userName + "님께서 요청하신 이메일 알람 서비스를 보내드립니다.</p>" +
                "<p><br/>고객님의 금융소득은 총: <strong>" + formattedSum + "</strong>원 입니다.</p>" +
                "<p>자세한 금융소득이 보고 싶으시다면,</p>" +
                "<p>하나택스 홈페이지의 [나의 금융소득 조회] -> [자세히 보기] 메뉴를 이용하시기 바랍니다.</p>" +
                "<p><br/><br/>고객 만족을 위해 최선을 다하는 하나택스가 되도록 노력하겠습니다. 감사합니다. <br/><br/></p>" +
                "<div style='background-color: #434749; color: white; padding: 35px 30px; text-align: left; margin-top: 20px; font-size: 10px; height:140px'>" +
                "<img src='data:image/png;base64," + base64Image2 + "' alt='Image' style='width:auto; height:40px; max-width:100%;'><br/><br/>" +
                "(주) 하나택스 | 서울특별시 중구 을지로 35 (을지로1가, 하나은행) | 사업자번호 : 202-81-14695<br/>" +
                "대표전화 1599-1111 / 1588-1111, 해외에서 거실 때 82-42-520-2500 / 82-2-3709-8080 <br/>" +
                "Copyright(c)2020 BY KEB HANA BANK. ALL right RESERVED. <br/></div>" +
                "</body>" +
                "</html>";
    }

    private void sendSmsAlert(String phoneNumber, FinancialIncomeDto income, String formattedSum) {
        String message = String.format("[하나택스] 고객님, 금융소득 종합과세 대상자에 대한 알림입니다.\n\n고객님의 금융소득은 총: %s원입니다.\n\n자세한 내용은 '하나택스 > 나의 금융소득 조회 > 자세히 보기' 메뉴를 확인해주세요.", formattedSum);
        smsService.sendMessage(phoneNumber, message);
        System.out.println("Sending SMS to " + phoneNumber + " about overtax on " + income.getInquiryDate());
    }

}
