package com.webapplication.demo.services;

import com.webapplication.demo.domain.Endereco;
import com.webapplication.demo.repositories.EnderecoRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository er;
    
    public Endereco buscar(Integer id) {
        Optional<Endereco> endereco = er.findById(id);
        
        return endereco.orElseThrow(() -> new ObjectNotFoundException("Endereco nao enconttrado"));
    }
}
