package com.webapplication.demo.services;

import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.domain.Pedido;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author CarlosMacaneta
 */
public abstract class AbstractEMailService implements EmailService {
    
    @Value("${default-sender}")
    private String sender; //incializando a var com o valor da prop
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido); 
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage();
        
        sm.setTo(pedido.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Código: "+pedido.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());
        
        return sm;
    }
    
    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
        try {
            MimeMessage mm = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(mm);
        } catch (MessagingException ex) {
            sendOrderConfirmationEmail(pedido);
        }
    }
    
    protected String htmlFromTemplatePedido(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return templateEngine.process("email/confirmacaoPedido", context);
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
        
        mmh.setTo(pedido.getCliente().getEmail());
        mmh.setTo(sender);
        mmh.setSubject("Pedido confirmado! Código: "+pedido.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(pedido), true);
        
        return mm;
    }
    
    @Override
    public void sendNewPasswordEmail(Cliente cliente, String newPassword) {
        SimpleMailMessage smm = prepareNewPasswordEmail(cliente, newPassword);
        sendEmail(smm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPassword) {
        SimpleMailMessage sm = new SimpleMailMessage();
        
        sm.setTo(cliente.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: "+newPassword);
        
        return sm;
    }
}
