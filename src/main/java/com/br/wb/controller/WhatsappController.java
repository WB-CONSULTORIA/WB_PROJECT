//package com.br.wb.controller;
//
//import com.br.wb.service.WhatsappService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/whatsapp")
//public class WhatsappController {
//
//	@Autowired
//	private WhatsappService whatsappService;
//
//	@PostConstruct
//	public void init() {
//		new Thread(() -> whatsappService.monitorNewMessages()).start();
//	}
//
//	@PostMapping("/{newContact}")
//	public void sendMessage(@PathVariable String newContact) {
//		whatsappService.newContact(newContact);
//	}
//}
