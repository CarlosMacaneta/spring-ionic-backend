package com.webapplication.demo.services;

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
import com.webapplication.demo.domain.enums.Perfil;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder passEncoder;
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

    public void instanciateTestDataBase() throws ParseException {
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
        Produto p4 = new Produto(null, "Mesa de escritorio", 300.00);
        Produto p5 = new Produto(null, "Toalha", 50.00);
        Produto p6 = new Produto(null, "Colcha", 200.00);
        Produto p7 = new Produto(null, "Tv true color", 1200.00);
        Produto p8 = new Produto(null, "Rocadeira", 800.00);
        Produto p9 = new Produto(null, "Abajur", 100.00);
        Produto p10 = new Produto(null, "Pendente", 180.00);
        Produto p11 = new Produto(null, "Mouse", 90.00);
        Produto p12 = new Produto(null, "Produto 12", 10.00);
        Produto p13 = new Produto(null, "Produto 13", 10.00);
        Produto p14 = new Produto(null, "Produto 14", 10.00);
        Produto p15 = new Produto(null, "Produto 15", 10.00);
        Produto p16 = new Produto(null, "Produto 16", 10.00);
        Produto p17 = new Produto(null, "Produto 17", 10.00);
        Produto p18 = new Produto(null, "Produto 18", 10.00);
        Produto p19 = new Produto(null, "Produto 19", 10.00);
        Produto p20 = new Produto(null, "Produto 20", 10.00);
        Produto p21 = new Produto(null, "Produto 21", 10.00);
        Produto p22 = new Produto(null, "Produto 22", 10.00);
        Produto p23 = new Produto(null, "Produto 23", 10.00);
        Produto p24 = new Produto(null, "Produto 24", 10.00);
        Produto p25 = new Produto(null, "Produto 25", 10.00);
        Produto p26 = new Produto(null, "Produto 26", 10.00);
        Produto p27 = new Produto(null, "Produto 27", 10.00);
        Produto p28 = new Produto(null, "Produto 28", 10.00);
        Produto p29 = new Produto(null, "Produto 29", 10.00);
        Produto p30 = new Produto(null, "Produto 30", 10.00);
        Produto p31 = new Produto(null, "Produto 31", 10.00);
        Produto p32 = new Produto(null, "Produto 32", 10.00);
        Produto p33 = new Produto(null, "Produto 33", 10.00);
        Produto p34 = new Produto(null, "Produto 34", 10.00);
        Produto p35 = new Produto(null, "Produto 35", 10.00);
        Produto p36 = new Produto(null, "Produto 36", 10.00);
        Produto p37 = new Produto(null, "Produto 37", 10.00);
        Produto p38 = new Produto(null, "Produto 38", 10.00);
        Produto p39 = new Produto(null, "Produto 39", 10.00);
        Produto p40 = new Produto(null, "Produto 40", 10.00);
        Produto p41 = new Produto(null, "Produto 41", 10.00);
        Produto p42 = new Produto(null, "Produto 42", 10.00);
        Produto p43 = new Produto(null, "Produto 43", 10.00);
        Produto p44 = new Produto(null, "Produto 44", 10.00);
        Produto p45 = new Produto(null, "Produto 45", 10.00);
        Produto p46 = new Produto(null, "Produto 46", 10.00);
        Produto p47 = new Produto(null, "Produto 47", 10.00);
        Produto p48 = new Produto(null, "Produto 48", 10.00);
        Produto p49 = new Produto(null, "Produto 49", 10.00);
        Produto p50 = new Produto(null, "Produto 50", 10.00);

        // categoria
        c1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
        c2.getProdutos().addAll(Arrays.asList(p2, p4));
        c3.getProdutos().addAll(Arrays.asList(p5, p6));
        c4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        c5.getProdutos().addAll(Arrays.asList(p8));
        c6.getProdutos().addAll(Arrays.asList(p9, p10));
        c7.getProdutos().addAll(Arrays.asList(p11));

        //produtos
        p1.getCategorias().addAll(Arrays.asList(c1, c4));
        p2.getCategorias().addAll(Arrays.asList(c1, c2, c4));
        p3.getCategorias().addAll(Arrays.asList(c1, c4));
        p4.getCategorias().addAll(Arrays.asList(c2));
        p5.getCategorias().addAll(Arrays.asList(c3));
        p6.getCategorias().addAll(Arrays.asList(c3));
        p7.getCategorias().addAll(Arrays.asList(c4));
        p8.getCategorias().addAll(Arrays.asList(c5));
        p9.getCategorias().addAll(Arrays.asList(c6));
        p10.getCategorias().addAll(Arrays.asList(c6));
        p11.getCategorias().addAll(Arrays.asList(c7));
        p12.getCategorias().add(c1);
        p13.getCategorias().add(c1);
        p14.getCategorias().add(c1);
        p15.getCategorias().add(c1);
        p16.getCategorias().add(c1);
        p17.getCategorias().add(c1);
        p18.getCategorias().add(c1);
        p19.getCategorias().add(c1);
        p20.getCategorias().add(c1);
        p21.getCategorias().add(c1);
        p22.getCategorias().add(c1);
        p23.getCategorias().add(c1);
        p24.getCategorias().add(c1);
        p25.getCategorias().add(c1);
        p26.getCategorias().add(c1);
        p27.getCategorias().add(c1);
        p28.getCategorias().add(c1);
        p29.getCategorias().add(c1);
        p30.getCategorias().add(c1);
        p31.getCategorias().add(c1);
        p32.getCategorias().add(c1);
        p33.getCategorias().add(c1);
        p34.getCategorias().add(c1);
        p35.getCategorias().add(c1);
        p36.getCategorias().add(c1);
        p37.getCategorias().add(c1);
        p38.getCategorias().add(c1);
        p39.getCategorias().add(c1);
        p40.getCategorias().add(c1);
        p41.getCategorias().add(c1);
        p42.getCategorias().add(c1);
        p43.getCategorias().add(c1);
        p44.getCategorias().add(c1);
        p45.getCategorias().add(c1);
        p46.getCategorias().add(c1);
        p47.getCategorias().add(c1);
        p48.getCategorias().add(c1);
        p49.getCategorias().add(c1);
        p50.getCategorias().add(c1);

        categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, 
                p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, 
                p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,p39, p40, p41, p42, p43, p44,
                p45, p46, p47, p48, p49, p50));

        Estado e1 = new Estado(null, "Mina Gerais");
        Estado e2 = new Estado(null, "Sao Paulo");

        Cidade c1_ = new Cidade(null, "Ubelandia", e1);
        Cidade c2_ = new Cidade(null, "Sao Paulo", e2);
        Cidade c3_ = new Cidade(null, "Campinas", e2);

        e1.getCidades().addAll(Arrays.asList(c1_));
        e2.getCidades().addAll(Arrays.asList(c1_, c3_));

        estadoRepository.saveAll(Arrays.asList(e1, e2));
        cidadeRepository.saveAll(Arrays.asList(c1_, c2_, c3_));

        Cliente cl1 = new Cliente(null, "Maria Silva", "carlosmacaneta95@gmail.com", "313484646", TipoCliente.PESSOA_FISICA, passEncoder.encode("123"));
        cl1.getTelefones().addAll(Arrays.asList("46516516546", "646160548"));

        Cliente cl2 = new Cliente(null, "Ana Costa", "carlosmacaneta@hotmail.com", "63916024418", TipoCliente.PESSOA_FISICA, passEncoder.encode("123"));
        cl2.addPerfil(Perfil.ADMIN);
        cl1.getTelefones().addAll(Arrays.asList("7826516546", "417060548"));

        Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "48648416", cl1, c1_);
        Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "846546516", cl1, c2_);
        Endereco endereco3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "210546516", cl2, c2_);

        cl1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
        cl1.getEnderecos().addAll(Arrays.asList(endereco3));

        clienteRepository.saveAll(Arrays.asList(cl1, cl2));
        enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3));

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
