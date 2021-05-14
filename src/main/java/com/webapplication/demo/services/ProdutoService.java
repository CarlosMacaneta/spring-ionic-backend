/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapplication.demo.services;

import com.webapplication.demo.domain.Produto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapplication.demo.repositories.ProdutoRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository pr;
    
    public Produto buscar(Integer id) {
        Optional<Produto> produto = pr.findById(id);
        
        return produto.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado."));
    }
}
