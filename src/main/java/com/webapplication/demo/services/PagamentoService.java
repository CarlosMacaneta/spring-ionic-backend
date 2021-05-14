package com.webapplication.demo.services;

import com.webapplication.demo.domain.Pagamento;
import com.webapplication.demo.repositories.PagamentoRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository pagamentoRepository;
    
    public Pagamento buscar(Integer id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        
        return pagamento.orElseThrow(() -> new ObjectNotFoundException("Pagamento nao encontrado"));
    }
}
