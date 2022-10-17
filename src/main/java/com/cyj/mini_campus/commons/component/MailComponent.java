package com.dokuny.mini_campus.commons.component;


import lombok.RequiredArgsConstructor;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailComponent {

    private final JavaMailSender javaMailSender;

    public boolean sendMail(String mail, String subject, String text) {

        boolean result = false;

        MimeMessagePreparator msg = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage, true, "UTF-8");

            mimeMessageHelper.setTo(mail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text,true);
        };

        try {
            javaMailSender.send(msg);
            result = true;

        } catch (Exception e) {
            throw new MailSendException("메일 전송에 실패했습니다.");
        }finally {
            return result;
        }

    }
}
