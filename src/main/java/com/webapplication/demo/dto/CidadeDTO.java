package com.webapplication.demo.dto;

import com.webapplication.demo.domain.Cidade;

/**
 *
 * @author CarlosMacaneta
 */
public class CidadeDTO {
    
    private Integer id;
    private String nome;

    public CidadeDTO() {
    }

    public CidadeDTO(Cidade cidade) {
        id = cidade.getId();
        nome = cidade.getNome();
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
