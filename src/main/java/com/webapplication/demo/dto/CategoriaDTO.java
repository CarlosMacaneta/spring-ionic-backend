package com.webapplication.demo.dto;

import com.webapplication.demo.domain.Categoria;
import java.io.Serializable;

/**
 *
 * @author CarlosMacaneta
 */
public class CategoriaDTO implements Serializable {
    
    private Integer id;
    private String name;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
        id = categoria.getId();
        name = categoria.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
