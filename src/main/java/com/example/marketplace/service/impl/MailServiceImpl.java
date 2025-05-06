package com.example.marketplace.service.impl;

import com.example.marketplace.entity.User;
import com.example.marketplace.entity.enums.MailType;
import com.example.marketplace.service.MailService;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final Configuration configuration;


    @Override
    public void sendMail(User user, MailType mailType, Properties properties) {
        switch(mailType) {
            case REGISTRATION -> sendRegistrationEmail(user, properties);
        }
    }

    @SneakyThrows
    private void sendRegistrationEmail(User user, Properties properties) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("Thank you for your registration," + user.getFirstName());
        helper.setTo(user.getUsername());
        String emailContent = getRegistrationEmailContent(user);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private String getRegistrationEmailContent(User user) {
        StringWriter writer = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getFirstName());
        configuration.getTemplate("register.ftlh").process(model, writer);
        return writer.getBuffer().toString();
    }

}
