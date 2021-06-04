package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Cidade;
import com.webapplication.demo.services.CidadeService;
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
@RequestMapping(value="/cidades")
public class CidadeResource {
    
    @Autowired
    private CidadeService cs;
    
    @RequestMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Cidade cidade = cs.findById(id);
        
        return ResponseEntity.ok().body(cidade);
    }
}
