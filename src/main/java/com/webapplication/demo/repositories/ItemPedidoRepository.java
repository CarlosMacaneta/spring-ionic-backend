package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CarlosMacaneta
 */
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    
}
