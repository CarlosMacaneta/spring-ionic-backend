package com.webapplication.demo.services;

import com.webapplication.demo.domain.PagamentoComBoleto;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class BoletoService {
    //trocar com codigo do web service
    public void preencherPagamentoComBoleto(PagamentoComBoleto boleto, Date instanteDoPedido) {
        Calendar c = Calendar.getInstance();
        c.setTime(instanteDoPedido);
        c.add(Calendar.DAY_OF_MONTH, 7);
        boleto.setDataVencimento(c.getTime());
    }
}
