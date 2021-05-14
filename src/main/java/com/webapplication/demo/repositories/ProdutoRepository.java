package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CarlosMacaneta
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
