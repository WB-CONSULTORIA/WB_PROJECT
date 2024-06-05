package com.br.wb.service;

import com.br.wb.domain.LastProcessMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService extends AbstractMongoEventListener<LastProcessMovement> {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void onAfterSave(AfterSaveEvent<LastProcessMovement> event) {
		LastProcessMovement objetoSalvo = event.getSource();
		sendEmail(objetoSalvo);
	}
	public void sendEmail(LastProcessMovement lastProcessMovement) {
		var message = new SimpleMailMessage();
		
		message.setFrom("joaomoreiraneris0@gmail.com");
		message.setTo("welcomebrasil119@gmail.com");
		message.setSubject("Movimentação no processo");
		message.setText("Houve uma movimentação no seu processo!");
		mailSender.send(message);
	}
}