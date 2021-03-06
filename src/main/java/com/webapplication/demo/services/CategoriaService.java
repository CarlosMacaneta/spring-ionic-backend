package com.webapplication.demo.services;

import com.webapplication.demo.domain.Categoria;
import com.webapplication.demo.dto.CategoriaDTO;
import com.webapplication.demo.repositories.CategoriaRepository;
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

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository cr;
    
    public Categoria save(Categoria categoria) {
        categoria.setId(null);//para garantir que o objecto seja criado e nao actualizado
        return cr.save(categoria);
    }
    
    public List<Categoria> findAllCategories() {
        return cr.findAll();
    }
    
    public Categoria findById(Integer id) {
        Optional<Categoria> categoria = cr.findById(id);
        
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada. Id:"+ " "+id));
    }
    
    /**
     * Este metodo edita/actualiza uma categoria.
     * @param categoria
     * @return 
     */
    public Categoria edit(CategoriaDTO categoria) {
        Categoria categ = findById(categoria.getId());
        return updateData(categ, fromDTO(categoria));
    }
    
    public void delete(Integer id) {
        findById(id);
        try {
            cr.deleteById(id);
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Nao e possivel apagar categoria com produtos");
        }
    }
    
    public Page<Categoria> findPage(Integer page, Integer size, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        
        return cr.findAll(pageRequest);
    }
    
    public Categoria fromDTO(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }
    
    /**
     * 
     * @param c1 the new object that is going to be saved with all new data
     * @param c2 the object with updated proprieties
     */
    private Categoria updateData(Categoria c1, Categoria c2) {
        c1.setNome(c2.getNome());
        
        return cr.save(c1);
    }
}
