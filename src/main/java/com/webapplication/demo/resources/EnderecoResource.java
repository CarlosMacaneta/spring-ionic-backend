package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Endereco;
import com.webapplication.demo.services.EnderecoService;
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
@RequestMapping(value="/enderecos")
public class EnderecoResource {
    
    @Autowired
    private EnderecoService es;
    
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Endereco endereco = es.buscar(id);
        
        return ResponseEntity.ok().body(endereco);
    }
}
