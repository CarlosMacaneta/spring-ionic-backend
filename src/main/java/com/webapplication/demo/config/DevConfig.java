package com.webapplication.demo.config;

import com.webapplication.demo.services.DBService;
import com.webapplication.demo.services.EmailService;
import com.webapplication.demo.services.SmtpEmailService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author CarlosMacaneta
 */
@Configuration
@Profile("dev")
public class DevConfig {
    
    @Autowired
    private DBService dBService;
    
    @Value("${spring.jpa.hibernate.ddl-auto}")//pega chave do application
    private String strategy; //
       
    @Bean
    public boolean instanciateDataBase() throws ParseException {
        
        if (!"create".equals(strategy)) {return false;}
        
        dBService.instanciateTestDataBase();             
        
        return true;
    }
    
    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
} 
