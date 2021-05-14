package com.webapplication.demo;

import com.webapplication.demo.domain.Categoria;
import com.webapplication.demo.domain.Cidade;
import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.domain.Endereco;
import com.webapplication.demo.domain.Estado;
import com.webapplication.demo.domain.ItemPedido;
import com.webapplication.demo.domain.Pagamento;
import com.webapplication.demo.domain.PagamentoComBoleto;
import com.webapplication.demo.domain.PagamentoComCartao;
import com.webapplication.demo.domain.Pedido;
import com.webapplication.demo.domain.Produto;
import com.webapplication.demo.domain.enums.EstadoPagamento;
import com.webapplication.demo.domain.enums.TipoCliente;
import com.webapplication.demo.repositories.CategoriaRepository;
import com.webapplication.demo.repositories.CidadeRepository;
import com.webapplication.demo.repositories.ClienteRepository;
import com.webapplication.demo.repositories.EnderecoRepository;
import com.webapplication.demo.repositories.EstadoRepository;
import com.webapplication.demo.repositories.ItemPedidoRepository;
import com.webapplication.demo.repositories.PagamentoRepository;
import com.webapplication.demo.repositories.PedidoRepository;
import com.webapplication.demo.repositories.ProdutoRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria c1 = new Categoria(null, "Informatica");
        Categoria c2 = new Categoria(null, "Escritorio");
        Categoria c3 = new Categoria(null, "Cama mesa e banho");
        Categoria c4 = new Categoria(null, "Codificar");
        Categoria c5 = new Categoria(null, "Comer");
        Categoria c6 = new Categoria(null, "Dormir");
        Categoria c7 = new Categoria(null, "Sonhar");
        
        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);
        
        // categoria
        c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        c2.getProdutos().addAll(Arrays.asList(p2));
        
        //produtos
        p1.getCategorias().addAll(Arrays.asList(c1));
        p2.getCategorias().addAll(Arrays.asList(c1, c2));
        p3.getCategorias().addAll(Arrays.asList(c1));
        
        categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
        
        Estado e1 = new Estado(null, "Mina Gerais");
        Estado e2 = new Estado(null, "Sao Paulo");
        
        Cidade c1_ = new Cidade(null, "Ubelandia", e1);
        Cidade c2_ = new Cidade(null, "Sao Paulo", e2);
        Cidade c3_ = new Cidade(null, "Campinas", e2);
        
        e1.getCidades().addAll(Arrays.asList(c1_));
        e2.getCidades().addAll(Arrays.asList(c1_, c3_));
        
        estadoRepository.saveAll(Arrays.asList(e1, e2));
        cidadeRepository.saveAll(Arrays.asList(c1_, c2_, c3_));
        
        Cliente cl1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "313484646", TipoCliente.PESSOA_FISICA);
        cl1.getTelefones().addAll(Arrays.asList("46516516546", "646160548"));
        
        Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "48648416", cl1, c1_); 
        Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "846546516", cl1, c2_);
        
        cl1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));
        
        clienteRepository.saveAll(Arrays.asList(cl1));
        enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
        
        //pedidos & pagamento
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, endereco1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, endereco2);
        
        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);
        
        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);
        
        cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));
        
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
        
        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
        
        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
        
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p2.getItens().addAll(Arrays.asList(ip2));
        
        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }

}
