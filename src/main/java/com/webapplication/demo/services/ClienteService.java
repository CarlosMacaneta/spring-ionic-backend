package com.webapplication.demo.services;

import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.dto.ClienteDTO;
import com.webapplication.demo.repositories.ClienteRepository;
import com.webapplication.demo.services.exceptions.DataIntegrityException;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository cr;
    
    public List<Cliente> findAll() {
        return cr.findAll();
    }
    
    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = cr.findById(id);
        
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cidade nao existe."));
    }
    
    public Page<Cliente> findPage(Integer page, Integer size, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        
        return cr.findAll(pageRequest);
    }
    
    public Cliente edit(ClienteDTO clienteDTO) {
        Cliente cliente = findById(clienteDTO.getId());
        return updateData(cliente, fromDTO(clienteDTO));
    }
    
    public void delete(Integer id) {
        findById(id);
        try {
            cr.deleteById(id);
        } catch(DataIntegrityException e) {
            throw new DataIntegrityException("Cliente nao pode ser removido porque ha entidades relacionadas");
        }
    }
    
    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }
    
    /**
     * 
     * @param c1 the new object that is going to be saved with all new data
     * @param c2 the object with updated proprieties
     */
    private Cliente updateData(Cliente c1, Cliente c2) {
        c1.setNome(c2.getNome());
        c1.setEmail(c2.getEmail());
        
        return cr.save(c1);
    }
}
