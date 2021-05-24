package com.webapplication.demo.services;

import com.webapplication.demo.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author CarlosMacaneta
 */
public interface EmailService {
    
    public void sendOrderConfirmationEmail(Pedido pedido);
    public void sendEmail(SimpleMailMessage msg);
}
