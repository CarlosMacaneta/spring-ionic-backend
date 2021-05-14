package com.webapplication.demo.services;

import com.webapplication.demo.domain.Categoria;
import com.webapplication.demo.repositories.CategoriaRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository cr;
    
    public Categoria buscar(Integer id) {
        Optional<Categoria> categoria = cr.findById(id);
        
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada. Id:"
                + " "+id+", Tipo: "+Categoria.class.getName()));
    }
}
