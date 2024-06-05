//package com.br.wb.service;
//
//import com.br.wb.domain.LastProcessMovement;
//import com.br.wb.dto.WhatsappMessageDTO;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
//import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class WhatsappService extends AbstractMongoEventListener<LastProcessMovement> {
//
//	@Autowired
//	private WebDriver webDriver;
//
//	@Override
//	public void onAfterSave(AfterSaveEvent<LastProcessMovement> event) {
//		LastProcessMovement objetoSalvo = event.getSource();
//		sendMessage(objetoSalvo);
//	}
//
//	public void sendMessage(LastProcessMovement lastProcessMovement) {
//		String text = "ALERTA: Houve uma movimentação no seu processo!!";
//		String contact = "NEUTRO";
//		WhatsappMessageDTO message = new WhatsappMessageDTO(contact, text);
//
//		try {
//			var elementContact = findContact(contact);
//			elementContact.click();
//
//			var labelText = findLabelText();
//			labelText.sendKeys(message.subject());
//			labelText.sendKeys(Keys.RETURN);
//
//			elementContact = findContact("NEUTRO");
//			elementContact.click();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void monitorNewMessages() {
//		while (true) {
//			try {
//				WebElement newMessageIndicator = findNewMessageIndicator();
//				if (newMessageIndicator != null) {
//					newMessageIndicator.click();
//
//					var labelText = findLabelText();
//					labelText.sendKeys("Este número está sendo usado para testes de automação, ignore esta mensagem.");
//					labelText.sendKeys(Keys.RETURN);
//				}
//				Thread.sleep(500);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//	public void newContact(String contactNumber) {
//		String xPathContact = "//*[@id=\"pane-side\"]/*//span[@title='NEUTRO']";
//		var elementNeutro = webDriver.findElement(By.xpath(xPathContact));
//		elementNeutro.click();
//		String xPathLabelText = "//*[@id=\"main\"]/footer//*[@role='textbox']";
//		var labelTextNeutro = webDriver.findElement(By.xpath(xPathLabelText));
//		labelTextNeutro.sendKeys(contactNumber);
//		labelTextNeutro.sendKeys(Keys.RETURN);
//
//		String xPathBase = "//*[@id=\"main\"]/div//*[@class='_amjv _aotl'][last()]//*[@class='_ao3e selectable-text copyable-text']/span";
//		WebElement lastMessageElement = webDriver.findElement(By.xpath(xPathBase));
//		lastMessageElement.click();
//
//		String xPathChat = "//*[@class=\"_ak5b\"]/div//*[@class='_aj-z _aj-t _alxo x1ye3gou xn6708d']";
//		while (true) {
//			WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(360));
//			WebElement chatElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathChat)));
//			if (chatElement.isDisplayed()) {
//				chatElement.click();
//				break;
//			}
//		}
//
//		xPathLabelText = "//*[@id=\"main\"]/footer//*[@role='textbox']";
//		String xPathTitle = "//*[@id=\"main\"]/header//*[@class='x1iyjqo2 x6ikm8r x10wlt62 x1n2onr6 xlyipyv xuxw1ft x1rg5ohu _ao3e']";
//		while (true) {
//			WebElement titleElement = webDriver.findElement(By.xpath(xPathTitle));
//
//			if (titleElement.getText().equals(contactNumber)) {
//				WebElement chatElement = webDriver.findElement(By.xpath(xPathLabelText));
//				chatElement.sendKeys("Olá, seu número foi adicionando no whatsapp da WB");
//				chatElement.sendKeys(Keys.RETURN);
//				break;
//			}
//		}
//	}
//
//	private WebElement findContact(String contactName) {
//		var xPathContact = "//*[@id=\"pane-side\"]/*//span[@title='" + contactName + "']";
//		return webDriver.findElement(By.xpath(xPathContact));
//	}
//
//	private WebElement findLabelText() {
//		var xPathLabelText = "//*[@id=\"main\"]/footer//*[@role='textbox']";
//		return webDriver.findElement(By.xpath(xPathLabelText));
//	}
//
//	private WebElement findNewMessageIndicator() {
//		try {
//			String att = "x1rg5ohu x173ssrc x1xaadd7 x682dto x1e01kqd x12j7j87 x9bpaai x1pg5gke x1s688f xo5v014 x1u28eo4 x2b8uid x16dsc37 x18ba5f9 x1sbl2l xy9co9w x5r174s x7h3shv";
//			var xPathNewMessageIndicator = "//*[@id=\"pane-side\"]/*//span[@class='" + att + "']";
//			return webDriver.findElement(By.xpath(xPathNewMessageIndicator));
//		} catch (Exception e) {
//			return null;
//		}
//	}
//}