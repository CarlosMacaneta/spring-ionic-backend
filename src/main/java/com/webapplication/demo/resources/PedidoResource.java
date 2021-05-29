package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Pedido;
import com.webapplication.demo.services.PedidoService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author CarlosMacaneta
 */
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
    
    @Autowired
    private PedidoService pedidoService;
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Pedido pedido) {
        Pedido p = pedidoService.save(pedido);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
        Pedido pedido = pedidoService.findById(id);
        
        return ResponseEntity.ok().body(pedido);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Pedido>> findPage(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "24") Integer size,
        @RequestParam(value = "direction",  defaultValue = "DESC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "instante") String orderBy){
        
        Page<Pedido> pedidos = pedidoService.findPage(page, size, direction, orderBy);
        
        return ResponseEntity.ok().body(pedidos);
    }
}
