package com.ditraacademy.travelagency.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

    @Autowired
    public JavaMailSender emailSender;
     @Async
    public void sendEmail(String destination , String subject , String text){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(destination);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
