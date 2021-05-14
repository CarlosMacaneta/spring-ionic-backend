package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CarlosMacaneta
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    
}
