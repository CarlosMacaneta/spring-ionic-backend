package com.webapplication.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.webapplication.demo.domain.enums.EstadoPagamento;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 *
 * @author CarlosMacaneta
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED) //superclass
public abstract class Pagamento implements Serializable {
    
    @Id//como pagamento tem relacionamento 1:1 com pedido nao precisa colocar o generated value
    private Integer id;
    private Integer estado;
    
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId//garante que o id do pagamento seja o mesmo com o id do pedido
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado == null ? null : estado.getId();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getId();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pagamento other = (Pagamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
