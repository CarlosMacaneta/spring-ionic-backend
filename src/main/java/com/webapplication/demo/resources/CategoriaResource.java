package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Categoria;
import com.webapplication.demo.dto.CategoriaDTO;
import com.webapplication.demo.services.CategoriaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author CarlosMacaneta
 */
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
    
    @Autowired //instancia o servico
    private CategoriaService service;
    
    /**
     * Este metodo recebe uma categoria no formato Json
     * e salva a mesma no banco de dados
     * 
     * @param categoria
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Categoria categoria) { //@RequestBody converte o Json para objecto java
        categoria = service.save(categoria);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();//retorna a uri do novo objecto criado
        
        return ResponseEntity.created(uri).build(); //retorna o codigo 201 que indica sucesso ao salvar novo objecto
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> categorias = service.findAllCategories().stream().map(c -> new CategoriaDTO(c)).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(categorias);
    }
    
    /*
     * O /{id} do request mapping e referente ao id de categoria fornecida pelo usuario
     * @param id refencia o id que sera passado para pela url
     * @return ResponseEntity por default ele ja encapsula todas informacoes http 
        de um servco rest
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok().body(categoria);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody Categoria categoria, @PathVariable Integer id) {
        categoria.setId(id);
        categoria = service.edit(categoria);
        
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
