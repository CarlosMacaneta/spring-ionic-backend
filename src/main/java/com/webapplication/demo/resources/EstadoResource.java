package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Estado;
import com.webapplication.demo.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CarlosMacaneta
 */
@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
    
    @Autowired
    private EstadoService es;
    
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Estado estado = es.buscar(id);
        
        return ResponseEntity.ok().body(estado);
    }
}
