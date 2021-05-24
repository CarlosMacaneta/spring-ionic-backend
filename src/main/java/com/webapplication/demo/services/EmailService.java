package com.webapplication.demo.services;

import com.webapplication.demo.domain.Pedido;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author CarlosMacaneta
 */
public interface EmailService {
    
    public void sendOrderConfirmationEmail(Pedido pedido);
    public void sendEmail(SimpleMailMessage msg);
    
    public void sendOrderConfirmationHtmlEmail(Pedido pedido);
    public void sendHtmlEmail(MimeMessage msg);
}
