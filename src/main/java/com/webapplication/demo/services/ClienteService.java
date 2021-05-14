package com.webapplication.demo.services;

import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.repositories.ClienteRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository cr;
    
    public Cliente buscar(Integer id) {
        Optional<Cliente> cliente = cr.findById(id);
        
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cidade nao existe."));
    }
}
