package com.group18.asdc.service;

import java.util.Enumeration;
import java.util.Properties;
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
		mailSender.setHost(mailSenderConfiguration.getHost());
		mailSender.setPort(mailSenderConfiguration.getPort());
		mailSender.setUsername(mailSenderConfiguration.getEmail());
		mailSender.setPassword(mailSenderConfiguration.getPassword());
		Properties props = mailSender.getJavaMailProperties();
		Properties defaultMailProperties = mailSenderConfiguration.getProperties();
		Enumeration<?> customPropsEnumerator = defaultMailProperties.propertyNames();
		while (customPropsEnumerator.hasMoreElements()) {
			String key = (String) customPropsEnumerator.nextElement();
			props.put(key, defaultMailProperties.getProperty(key));
		}
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