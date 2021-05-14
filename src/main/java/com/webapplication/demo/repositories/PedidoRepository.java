package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CarlosMacaneta
 */
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
}
