package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CarlosMacaneta
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    
    @Transactional(readOnly = true)
    public List<Estado> findAllByOrderByNome();
}
