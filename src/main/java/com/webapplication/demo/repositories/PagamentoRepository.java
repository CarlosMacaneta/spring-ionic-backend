package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CarlosMacaneta
 */
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    
}
