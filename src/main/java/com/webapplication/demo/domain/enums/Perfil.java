package com.webapplication.demo.domain.enums;

/**
 *
 * @author CarlosMacaneta
 */
public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");
    
    private int id;
    private String descricao;

    private Perfil(int id, String descricao) {
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
    
    public static Perfil toEnum(Integer id) {
        if (id == null) return null;
        
        for (Perfil ep: Perfil.values()) {
            if (ep.getId() == id) {
                return ep;
            }
        }
        
        throw new IllegalArgumentException("Id inválido: "+id);
    }
}
