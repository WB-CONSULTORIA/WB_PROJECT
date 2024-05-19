package com.br.wb.service;

import com.br.wb.dto.WhatsappMessageDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {

	@Autowired
	private WebDriver webDriver;

	public void sendMessage(WhatsappMessageDTO mensagem) {
		for (String contato : mensagem.contacts()) {
			try {
				var elementoContato = findContact(contato);
				elementoContato.click();

				var caixaMensagem = findLabelText();
				caixaMensagem.sendKeys(mensagem.subject());
				caixaMensagem.sendKeys(Keys.RETURN);

				elementoContato = findContact("Notação Científica");
				elementoContato.click();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void monitorNewMessages() {
		while (true) {
			try {
				WebElement newMessageIndicator = findNewMessageIndicator();
				if (newMessageIndicator != null) {
					newMessageIndicator.click();

					var caixaMensagem = findLabelText();
					caixaMensagem.sendKeys("Este número está sendo usado para testes de automação, tente novamente mais tarde.");
					caixaMensagem.sendKeys(Keys.RETURN);
				}
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private WebElement findContact(String nomeContato) {
		var xPathContact = "//*[@id=\"pane-side\"]/*//span[@title='" + nomeContato + "']";
		return webDriver.findElement(By.xpath(xPathContact));
	}

	private WebElement findLabelText() {
		var xPathLabelText = "//*[@id=\"main\"]/footer//*[@role='textbox']";
		return webDriver.findElement(By.xpath(xPathLabelText));
	}

	private WebElement findNewMessageIndicator() {
		try {
			String att = "x1rg5ohu x173ssrc x1xaadd7 x682dto x1e01kqd x12j7j87 x9bpaai x1pg5gke x1s688f xo5v014 x1u28eo4 x2b8uid x16dsc37 x18ba5f9 x1sbl2l xy9co9w x5r174s x7h3shv";
			var xPathNewMessageIndicator = "//*[@id=\"pane-side\"]/*//span[@class='" + att + "']";
			return webDriver.findElement(By.xpath(xPathNewMessageIndicator));
		} catch (Exception e) {
			return null;
		}
	}
}