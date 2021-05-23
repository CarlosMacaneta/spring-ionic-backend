package com.webapplication.demo.services;

import com.webapplication.demo.domain.ItemPedido;
import com.webapplication.demo.domain.PagamentoComBoleto;
import com.webapplication.demo.domain.Pedido;
import com.webapplication.demo.domain.enums.EstadoPagamento;
import com.webapplication.demo.repositories.ItemPedidoRepository;
import com.webapplication.demo.repositories.PagamentoRepository;
import com.webapplication.demo.repositories.PedidoRepository;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private PagamentoRepository pagRepository;
    
    @Autowired
    private BoletoService boletoService;
    
    @Autowired
    private ItemPedidoRepository itemRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @Transactional
    public Pedido save(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        //salvando pagamento
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto boleto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(boleto, pedido.getInstante());
        }
        pedido = pedidoRepository.save(pedido);
        pagRepository.save(pedido.getPagamento());
        
        //salvando os itens
        for (ItemPedido item: pedido.getItens()) {
            item.setDesconto(0.00);
            item.setProduto(produtoService.findById(item.getProduto().getId()));
            item.setPreco(item.getProduto().getPreco());
            item.setPedido(pedido);
        }
        itemRepository.saveAll(pedido.getItens());
        System.out.println(pedido);
        return pedido;
    }
    
    public Pedido findById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido nao encontrado"));
    }
}
