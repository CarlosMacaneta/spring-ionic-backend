package com.webapplication.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author CarlosMacaneta
 */
public class SmtpEmailService extends AbstractEMailService {
    
    @Autowired
    private MailSender mailSender; //instancia com todas as propriedades do servico de email que estao em application-dev/prod
    
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando de email...");
        mailSender.send(msg);
        LOG.info("Email enviado");
    }
}
