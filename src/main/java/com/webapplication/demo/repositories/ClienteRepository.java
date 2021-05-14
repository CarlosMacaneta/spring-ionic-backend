package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CarlosMacaneta
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
