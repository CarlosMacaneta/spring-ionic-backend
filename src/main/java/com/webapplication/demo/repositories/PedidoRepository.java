package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CarlosMacaneta
 */
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
    @Transactional(readOnly = true)
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
