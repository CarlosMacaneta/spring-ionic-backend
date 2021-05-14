package com.webapplication.demo.domain;

import com.webapplication.demo.domain.enums.EstadoPagamento;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author CarlosMacaneta
 */
@Entity
public class PagamentoComCartao extends Pagamento {
    
    
    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
    
}
