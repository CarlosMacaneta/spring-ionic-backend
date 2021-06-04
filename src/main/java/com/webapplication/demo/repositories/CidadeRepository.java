package com.webapplication.demo.repositories;

import com.webapplication.demo.domain.Cidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CarlosMacaneta
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    
    @Transactional(readOnly = true)
    public List<Cidade> findCidadesByEstadoIdOrderByNome(Integer id);
}
