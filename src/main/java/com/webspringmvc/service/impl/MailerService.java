package com.webspringmvc.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailer")
public class MailerService {
	@Autowired
	JavaMailSender mailer;

	public void send(String from, String to, String subject, String body) {
		try {
			// tạo mail
			MimeMessage mail = mailer.createMimeMessage();
			// sử dụng lớp trợ giúp
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom("contact@gmail.com", from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
	
			// gửi mail
			mailer.send(mail);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
