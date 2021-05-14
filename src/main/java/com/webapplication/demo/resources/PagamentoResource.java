package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Pagamento;
import com.webapplication.demo.services.PagamentoService;
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
@RequestMapping("/pagamentos")
public class PagamentoResource {
    
    @Autowired
    private PagamentoService pagamentoService;
    
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Pagamento pagamento = pagamentoService.buscar(id);
        
        return ResponseEntity.ok().body(pagamento);
    }
}
