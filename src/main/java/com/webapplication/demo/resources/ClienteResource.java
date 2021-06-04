package com.webapplication.demo.resources;

import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.dto.ClienteDTO;
import com.webapplication.demo.dto.ClienteNewDTO;
import com.webapplication.demo.services.ClienteService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author CarlosMacaneta
 */
@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
    
    @Autowired
    private ClienteService cs;
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody ClienteNewDTO cliente) {
        Cliente c = cs.save(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}").buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> list = cs.findAll().stream().map(c -> new ClienteDTO(c)).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        Cliente cliente = cs.findById(id);
        
        return ResponseEntity.ok().body(cliente);
    }
    
    @RequestMapping(value="/email", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findByEmail(@RequestParam(value="email") String email) {
        Cliente cliente = cs.findByEmail(email);
        
        return ResponseEntity.ok().body(cliente);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
        @RequestParam(value = "page", defaultValue = "0") Integer page, 
        @RequestParam(value = "size", defaultValue = "24") Integer size, 
        @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
        @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        
        Page<Cliente> pages = cs.findPage(page, size, direction, orderBy);
        Page<ClienteDTO> pagesDTO = pages.map(c -> new ClienteDTO(c));
        
        return ResponseEntity.ok().body(pagesDTO);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
        clienteDTO.setId(id);
        Cliente cliente = cs.edit(clienteDTO);
        
        return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Integer id) {
        cs.delete(id);
        
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value= "/picture",method = RequestMethod.POST)
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
        URI uri = cs.updoadProfilePicture(file);
        
        return ResponseEntity.created(uri).build();
    }
}
