package com.webapplication.demo.dto;

import com.webapplication.demo.domain.Estado;

/**
 *
 * @author CarlosMacaneta
 */
public class EstadoDTO {
    
    private Integer id;
    private String nome;

    public EstadoDTO() {
    }
    
    public EstadoDTO(Estado estado) {
        id = estado.getId();
        nome = estado.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
