package com.webapplication.demo.services;

import com.webapplication.demo.domain.Estado;
import com.webapplication.demo.repositories.EstadoRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository er;
    
    public Estado buscar(Integer id) {
        Optional<Estado>  estado = er.findById(id);
        
        return estado.orElseThrow(()-> new ObjectNotFoundException("Estado nao encontrado"));
    }
}
