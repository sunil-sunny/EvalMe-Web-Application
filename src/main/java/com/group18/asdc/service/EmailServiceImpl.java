package com.group18.asdc.service;

import java.util.Properties;

import com.group18.asdc.util.IJavaMailSenderConfiguration;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailServiceImpl implements EmailService {

    private JavaMailSender emailSender;

    public EmailServiceImpl(IJavaMailSenderConfiguration mailSenderConfiguration) {
        emailSender = getJavaMailSender(mailSenderConfiguration);
    }

    private JavaMailSender getJavaMailSender(IJavaMailSenderConfiguration mailSenderConfiguration) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(mailSenderConfiguration.getEmail());
        mailSender.setPassword(mailSenderConfiguration.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

}