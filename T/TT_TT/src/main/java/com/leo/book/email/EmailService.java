package com.leo.book.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private  final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String from;
    private ValidCode validCode;
    private static final SecureRandom secureRandom = new SecureRandom();

    @Async
    public void sendEmail(
            String to,
            String subject,
            String template
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(from);

        Context context = new Context();
        String code = generateSecureCode();
        context.setVariable("code", code);
        validCode = new ValidCode(code);

        //just for fun
        /*FileSystemResource file = new FileSystemResource(new File("/home/leo/Downloads/bg.jpg"));
        helper.addAttachment(file.getFilename(), file);*/
        //

        helper.setText(templateEngine.process(template, context), true);

        mailSender.send(message);
    }

    /*private String getCode(){
        Random rm = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 8; i++){
            int r = rm.nextInt(26);
            char ch = (char) (r + 65);
            code.append(ch);
        }
        return code.toString();
    }*/

    private String generateSecureCode(){
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
