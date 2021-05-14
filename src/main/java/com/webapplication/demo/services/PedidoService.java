package com.webapplication.demo.services;

import com.webapplication.demo.domain.Pedido;
import com.webapplication.demo.repositories.PedidoRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    public Pedido buscar(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido nao encontrado"));
    }
}
