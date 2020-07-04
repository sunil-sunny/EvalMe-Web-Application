package com.group18.asdc.service;

public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String text);
}