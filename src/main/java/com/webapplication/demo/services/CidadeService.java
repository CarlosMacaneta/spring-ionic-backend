package com.webapplication.demo.services;

import com.webapplication.demo.domain.Cidade;
import com.webapplication.demo.repositories.CidadeRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class CidadeService {
    
    @Autowired
    private CidadeRepository cr;
    
    
    public List<Cidade> findCidadesByEstadoIdByOrderByNome(Integer estadoId) {
        return cr.findCidadesByEstadoIdOrderByNome(estadoId);
    }
    
    public Cidade findById(Integer id) {
        Optional<Cidade> cidade = cr.findById(id);
        
        return cidade.orElseThrow(() -> new ObjectNotFoundException("Cidade nao encotrada."));
    }
}
