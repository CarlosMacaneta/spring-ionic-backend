package com.webapplication.demo.services;

import com.webapplication.demo.domain.Pedido;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author CarlosMacaneta
 */
public class MockEMailService extends AbstractEMailService {
    
    private static final Logger LOG = LoggerFactory.getLogger(MockEMailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando envio de email...");
        LOG.info(msg.toString());
        LOG.info("Email enviado");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Simulando envio de email HMTL...");
        LOG.info(msg.toString());
        LOG.info("Email enviado");
    }
    
}
