package com.webapplication.demo.services;

import com.webapplication.demo.domain.Categoria;
import com.webapplication.demo.domain.Produto;
import com.webapplication.demo.repositories.CategoriaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapplication.demo.repositories.ProdutoRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository pr;
    
    @Autowired
    private CategoriaRepository cr;
    
    public Produto findById(Integer id) {
        Optional<Produto> produto = pr.findById(id);
        
        return produto.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado."));
    }
    
    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer size, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        
        List<Categoria> categorias = cr.findAllById(ids);
        
        return pr.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
