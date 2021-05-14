package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.services.ClienteService;
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
@RequestMapping(value="/clientes")
public class ClienteResource {
    
    @Autowired
    private ClienteService cs;
    
    @RequestMapping(value="/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Cliente cliente = cs.buscar(id);
        
        return ResponseEntity.ok().body(cliente);
    }
}
