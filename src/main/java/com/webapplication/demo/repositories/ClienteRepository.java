package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CarlosMacaneta
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    @Transactional(readOnly = true)
    public Cliente findByEmail(String email);
}
