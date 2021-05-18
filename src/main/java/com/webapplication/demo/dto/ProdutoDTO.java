package com.webapplication.demo.dto;

import com.webapplication.demo.domain.Produto;

/**
 *
 * @author CarlosMacaneta
 */
public class ProdutoDTO {
    
    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public ProdutoDTO(Produto p) {
        id = p.getId();
        nome = p.getNome();
        preco = p.getPreco();
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
        
}
