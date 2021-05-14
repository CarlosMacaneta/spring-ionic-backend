package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CarlosMacaneta
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    
}
