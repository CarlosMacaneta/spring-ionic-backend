package com.webapplication.demo.domain.enums;

/**
 *
 * @author CarlosMacaneta
 */
public enum EstadoPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    PARCELADO(3, "Parcelado");
    
    private int id;
    private String descricao;

    private EstadoPagamento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public static EstadoPagamento toEnum(Integer id) {
        if (id == null) return null;
        
        for (EstadoPagamento ep: EstadoPagamento.values()) {
            if (ep.getId() == id) {
                return ep;
            }
        }
        
        throw new IllegalArgumentException("Id inválido: "+id);
    }
}
