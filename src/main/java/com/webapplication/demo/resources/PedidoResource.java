package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Pedido;
import com.webapplication.demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author CarlosMacaneta
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
    
    @Autowired
    private PedidoService pedidoService;
    
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Pedido pedido = pedidoService.buscar(id);
        
        return ResponseEntity.ok().body(pedido);
    }
}
