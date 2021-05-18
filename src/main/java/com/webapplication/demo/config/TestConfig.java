package com.webapplication.demo.config;

import com.webapplication.demo.services.DBService;
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
}
