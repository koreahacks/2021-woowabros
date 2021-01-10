package koreahacks.woowabros.uniconn.member.application;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    private String hostURL;

    @Override
    public void sendAuthEmail(String targetEmail, String authCode) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        createMessage(targetEmail, authCode, helper);
        emailSender.send(message);
    }

    private void createMessage(String targetEmail, String authCode, MimeMessageHelper helper) {
        try {
            helper.setFrom("uniconn2021@gmail.com");
            helper.setTo(targetEmail);
            helper.setSubject("유니콘 인증 메일");
            helper.setText("<h1>안녕하세요. 유니콘입니다. 다음 링크를 클릭해주세요.</h1>"
                + "<h2>https://uniconn.me/api/members/auth?code=" + authCode + "</h2>", true);
        } catch (MessagingException e) {
            throw new IllegalArgumentException();
        }
    }
}
