package com.webapplication.demo.services;

import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.repositories.ClienteRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class AuthService {
    
    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCrypt;
    
    @Autowired
    private EmailService emailService;
    
    private Random rand = new Random();
    
    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        
        if (cliente == null) throw new ObjectNotFoundException("Email não encontrado.");
        
        String newPassword = newPassword();
        cliente.setSenha(bCrypt.encode(newPassword));
        
        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPassword);
    }

    private String newPassword() {
        char[] pass = new char[10];
        
        for (int i = 0; i < pass.length; i++) {
            pass[i] = randomChar();
        }
        return new String(pass);
    }

    private char randomChar() {
        
        switch (rand.nextInt(3)) {
            case 0:
                //gerar digito
                return (char) (rand.nextInt(10) + 48);
            case 1:
                //gera letra maiuscula
                return (char) (rand.nextInt(26) + 65);
            default:
                //letra min
                return (char) (rand.nextInt(26) + 97);
        }
    }
}
