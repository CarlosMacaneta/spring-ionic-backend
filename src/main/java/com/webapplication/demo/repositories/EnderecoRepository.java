package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CarlosMacaneta
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
    
}
