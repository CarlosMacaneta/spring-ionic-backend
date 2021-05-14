package com.webapplication.demo.services;

import com.webapplication.demo.domain.ItemPedido;
import com.webapplication.demo.repositories.ItemPedidoRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class ItemPedidoService {
    
    @Autowired
    private ItemPedidoRepository itemRepository;
    
    public ItemPedido buscar(Integer id) {
        Optional<ItemPedido> item = itemRepository.findById(id);
        
        return item.orElseThrow(() -> new ObjectNotFoundException("Item pedido nao encontrado"));
    }
}
