package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Categoria;
import com.webapplication.demo.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CarlosMacaneta
 */
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
    
    @Autowired //instancia o servico
    private CategoriaService service;
    
    /*
     * O /{id} do request mapping e referente ao id de categoria fornecida pelo usuario
     * @param id refencia o id que sera passado para pela url
     * @return ResponseEntity por default ele ja encapsula todas informacoes http 
        de um servco rest
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        
        Categoria categoria = service.buscar(id);
        return ResponseEntity.ok().body(categoria);
    }
}
