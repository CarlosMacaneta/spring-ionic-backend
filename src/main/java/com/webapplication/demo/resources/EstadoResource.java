package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Estado;
import com.webapplication.demo.dto.CidadeDTO;
import com.webapplication.demo.dto.EstadoDTO;
import com.webapplication.demo.services.CidadeService;
import com.webapplication.demo.services.EstadoService;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping(value="/estados")
public class EstadoResource {
    
    @Autowired
    private EstadoService es;
    
    @Autowired
    private CidadeService cs;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAllByOrderByNome() {
        List<EstadoDTO> estados = es.findAllByOrderByNome().stream().map(e -> new EstadoDTO(e)).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(estados);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Estado> findById(@PathVariable Integer id) {
        Estado estado = es.findById(id);
        
        return ResponseEntity.ok().body(estado);
    }
    
    @RequestMapping(value="/{estadoId}/cidades",method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findCidadesByEstadoIdByOrderByNome(@PathVariable Integer estadoId) {
        List<CidadeDTO> cidades = cs.findCidadesByEstadoIdByOrderByNome(estadoId).stream().map(c -> new CidadeDTO(c)).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(cidades);
    }
}
