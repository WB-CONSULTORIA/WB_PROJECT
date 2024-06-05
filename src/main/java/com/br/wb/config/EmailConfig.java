package com.br.wb.config;


import com.br.wb.service.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class EmailConfig {

    @Bean
    public EmailService emailNotificationListener() {
        return new EmailService();
    }


}
