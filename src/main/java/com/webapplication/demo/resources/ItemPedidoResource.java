package com.webapplication.demo.resources;

import com.webapplication.demo.domain.ItemPedido;
import com.webapplication.demo.services.ItemPedidoService;
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
@RequestMapping("/itens-pedidos")
public class ItemPedidoResource {
    
    @Autowired
    private ItemPedidoService itemService;
    
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        ItemPedido item = itemService.buscar(id);
        
        return ResponseEntity.ok().body(item);
    }
}
