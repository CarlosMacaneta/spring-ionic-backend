package com.webapplication.demo.services;

import com.webapplication.demo.domain.Cidade;
import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.domain.Endereco;
import com.webapplication.demo.domain.enums.TipoCliente;
import com.webapplication.demo.dto.ClienteDTO;
import com.webapplication.demo.dto.ClienteNewDTO;
import com.webapplication.demo.repositories.ClienteRepository;
import com.webapplication.demo.repositories.EnderecoRepository;
import com.webapplication.demo.services.exceptions.DataIntegrityException;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository cr;
    @Autowired
    private EnderecoRepository er;
    
    @Transactional//garante o cliente o endereco sejam salvos na mesma transacao do banco de dados
    public Cliente save(ClienteNewDTO clienteNewDTO) {
        Cliente cliente = cr.save(fromNewDTO(clienteNewDTO));
        er.saveAll(cliente.getEnderecos());
        return cliente;
    }
    
    public List<Cliente> findAll() {
        return cr.findAll();
    }
    
    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = cr.findById(id);
        
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente nao existe."));
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
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cliente nao pode ser removido porque ha pedidos relacionadas");
        }
    }
    
    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }
    
    public Cliente fromNewDTO(ClienteNewDTO clienteDTO) {
        Cliente cliente = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), 
                clienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteDTO.getTipoCliente()));
        
        Cidade cidade = new Cidade(clienteDTO.getCidadeId(), null, null);
        
        Endereco endereco = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento(), 
                clienteDTO.getBairro(), clienteDTO.getCep(), cliente, cidade);
        
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteDTO.getTelefone1());
        
        if (clienteDTO.getTelefone2() != null && !clienteDTO.getTelefone2().equals(clienteDTO.getTelefone1())) {
            cliente.getTelefones().add(clienteDTO.getTelefone2());
        }
        if (clienteDTO.getTelefone3() != null && !clienteDTO.getTelefone3().equals(clienteDTO.getTelefone1())
                && !clienteDTO.getTelefone3().equals(clienteDTO.getTelefone2())) {
            cliente.getTelefones().add(clienteDTO.getTelefone3());
        }
        return cliente;
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
