package com.webapplication.demo.config;

import com.webapplication.demo.services.DBService;
import com.webapplication.demo.services.EmailService;
import com.webapplication.demo.services.MockEMailService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author CarlosMacaneta
 */
@Configuration
@Profile("test")
public class TestConfig {
    
    @Autowired
    private DBService dBService;
       
    @Bean
    public boolean instanciateDataBase() throws ParseException {
        dBService.instanciateTestDataBase();
        
        return true;
    }
    
    @Bean //metodos com anotacao bean fica visivel como um componente do sistema
    public EmailService emailService() {
        return new MockEMailService();
    }
}
