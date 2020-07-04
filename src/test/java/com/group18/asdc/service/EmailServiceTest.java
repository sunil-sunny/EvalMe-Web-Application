package com.group18.asdc.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTest {

	@Mock
	IJavaMailSenderConfiguration javaMailSenderConfiguration = new JavaMailSenderConfigurationMock();

	@InjectMocks
	EmailService emailService = new EmailServiceImpl(javaMailSenderConfiguration);

	@Mock
	JavaMailSender mailSender;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void checkEmails() {
		emailService.sendSimpleMessage("kr630601@dal.ca", "Reset", "Your Password");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("kr630601@dal.ca");
		message.setSubject("Reset");
		message.setText("Your Password");
		verify(mailSender, times(1)).send(message);
	}
}