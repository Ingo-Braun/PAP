/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Modelo.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ejb.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.Serializable;
import java.net.URI;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ingo
 */
@ManagedBean
@SessionScoped
public class TesteMB implements Serializable {

    private static final long serialVersionUID = 1744638741234687348L;

    @EJB
    TelefoneRemote TelefoneR;
    @EJB
    ClienteRemote clienteR;
    @EJB
    EnderecoRemote EnderecoR;
    @EJB
    EntregaRemote EntregaR;
    @EJB
    EstoqueRemote EstoqueR;
    @EJB
    FentregaRemote FentregaR;
    @EJB
    PedidoRemote PedidoR;
    @EJB
    PgtoRemote PgtoR;
    @EJB
    ProdutoRemote ProdutoR;
    @EJB
    VendaRemote VendaR;
    @EJB
    CardapioRemote CardapioR;

    public EnderecoJSON ejson = new EnderecoJSON();
    public Cliente cli = new Cliente();
    public Telefone tel = new Telefone();
    public Endereco e = new Endereco();
    public String telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Telefone getTel() {
        return tel;
    }

    public void setTel(Telefone tel) {
        this.tel = tel;
    }

    public Endereco getE() {
        return e;
    }

    public void setE(Endereco e) {
        this.e = e;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public EnderecoJSON getEjson() {
        return ejson;
    }

    public void setEjson(EnderecoJSON ejson) {
        this.ejson = ejson;
    }

    public String getLogado() {
        String resposta = "";
        if (SessionContext.getInstance().getAttribute("usuarioLogado") != null) {
            Cliente c = (Cliente) SessionContext.getInstance().getAttribute("usuarioLogado");
            resposta = c.getNome();
        } else {
            resposta = "nada";
        }
        return resposta;
    }

    public String login() {
        Cliente c = new Cliente();
        c.setNome("teste 1");
        SessionContext.getInstance().setAttribute("usuarioLogado", c);
        return "index";
    }

    public String logout() {
        SessionContext.getInstance().encerrarSessao();
        return "index";
    }

    public String salvarCliente() {
        try {
            tel.setTelefone(Integer.parseInt(telefone));
            List<Telefone> TelefoneList = new ArrayList();
            List<Endereco> EnderecoList = new ArrayList();
            cli.setEnderecoList(EnderecoList);
            cli.setTelefoneList(TelefoneList);
            cli.setDatacria(new Date());
            clienteR.Salvar(cli);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    public String iniciar() throws Exception {

        Cliente cli = new Cliente("Nome 1", "Sobrenome 1", "Npome1@email.com", "123", "123456789", new Date());
        List<Endereco> enderecoList = new LinkedList();
        Endereco end = new Endereco("rua 1", "cidade 1", "estado 1", 123, "nada", new Date());
        enderecoList.add(end);
        cli.setEnderecoList(enderecoList);
        List<Cliente> clienteList = new LinkedList();
        clienteList.add(cli);

        List<Telefone> telefoneList = new LinkedList();
        telefoneList.add(new Telefone(12345678, Short.parseShort("0"), new Date()));
        cli.setTelefoneList(telefoneList);

        System.out.println("Chamando ejb " + new Date());
        try {
            System.out.println("Salvando Telefones");
            for (int i = 0; i < telefoneList.size(); i++) {
                telefoneList.set(i, TelefoneR.salvar(telefoneList.get(i)));
            }
            System.out.println("Salvando Enderecos");
            for (int i = 0; i < enderecoList.size(); i++) {
                enderecoList.set(i, EnderecoR.salvar(enderecoList.get(i)));
            }

            System.out.println("Salvando novo estoque");
            EstoqueR.salvar(new Estoque(1, new Date()));

            System.out.println("salvando novo produto");
            Produto produto = new Produto("Prod 1", "teste", 1.1D, new Date());
            produto.setEstoque(EstoqueR.salvar(new Estoque(1, new Date())));
            System.out.println("id do estoque "+ produto.getEstoque().getId());
            produto = ProdutoR.salvar(produto);
            System.out.println("Produto salvo id " + produto.getId());

            List<Venda> vendaList = new ArrayList();

            System.out.println("Salvando venda");
            Venda venda = new Venda(1, new Date());
            venda.setIdProduto(produto);
            venda = VendaR.salvar(venda);
            vendaList.add(venda);
            System.out.println("Salvando venda");
            venda = new Venda(1, new Date());
            venda.setIdProduto(produto);
            venda = VendaR.salvar(venda);
            vendaList.add(venda);

            System.out.println("Salvando pedido");
            Pedido pedido = new Pedido(Short.parseShort("1"), Short.parseShort("1"), new Date());
            pedido.setFormaentrega(FentregaR.salvar(new Fentrega("teste 1", "nada", "sem desc", new Date())));
            pedido.setPgto(PgtoR.salvar(new Pgto("pgto 1", "dinheiro", "money", new Date())));
            pedido.setVendaList(vendaList);
            short numero = (short) pedido.getVendaList().size();
            pedido.setQtditems(numero);
            pedido = PedidoR.salvar(pedido);

            System.out.println("salvando entrega");
            Entrega entrega = new Entrega(new Date(), new Date());
            entrega.setEndereco(enderecoList.get(0));
            entrega.setPedido(pedido);
            entrega = EntregaR.salvar(entrega);

            try {
                System.out.println("Salvando novo cardapio " + new Date());
                Cardapio card = new Cardapio();
                card.setNome("cardapio 1");
                card.setDescricao("Descriçao 1");
                card.setImg("img 1");
                List<Produto> prodList = new ArrayList();
                System.out.println("Produto id " + produto.getId());
                prodList.add(produto);
                card.setProdutoList(prodList);
                card = CardapioR.salvar(card);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            List<Pedido> pedidoList = new LinkedList();
            pedidoList.add(pedido);
            cli.setPedidoList(pedidoList);
            System.out.println("Salvando Cliente");
            cli = clienteR.Salvar(cli);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("");
        System.out.println("ID salvo " + cli.getId());
        System.out.println("Nome salvo " + cli.getNome());

        try {
            List<Pedido> pedidoList = PedidoR.BuscarTodos();
            for (int i = 0; i < pedidoList.size(); i++) {
                System.out.println("Pedido salvo id " + pedidoList.get(i).getId() + " qtd " + pedidoList.get(i).getQtditems());
            }
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar todos os pedidos " + new Date() + "     " + e.getMessage());
        }

        System.out.println("Buscando descricao de Formas de entrega");
        try {
            List<Fentrega> fentregaList = FentregaR.BuscarPorForma("na");
            for (int i = 0; i < fentregaList.size(); i++) {
                System.out.println("Forma de entrega  id " + fentregaList.get(i).getId() + " nome " + fentregaList.get(i).getNome());
            }
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar todos os pedidos " + new Date() + "      " + e.getMessage());
        }

        System.out.println("senha salva em SHA-256");
        System.out.println(cli.getSenha());
        APIlocal(end);

        return "index";
    }

    public void RemoverTodos() throws Exception {
        try {
            List<Cliente> clienteList = clienteR.getAllCliente();
            for (int i = 0; i < clienteList.size(); i++) {
                System.out.println("Removendo Cliente com id " + clienteList.get(i).getId());
                clienteR.remover(clienteList.get(i).getId());
            }
            System.out.println("Remocao de clientes concluida");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void APIlocal(Endereco e) {
        Client c = new Client();
        Gson gson = new Gson();
        // R. Alfeu Taváres, 149 - Rudge Ramos, São Bernardo do Campo - SP
        ejson.setN("149");
        ejson.setRua("Alfeu Taváres");
        ejson.setCidade("São Bernardo do Campo");
        WebResource wr;
        String json = "";
        try {
            URI uri = URI.create(ejson.getLink());
            wr = c.resource(uri);
            System.out.println("tentando get Local");
            wr.header("Content-Type", "application/json;charset=UTF-8");
            json = wr.get(String.class);
            System.out.println("JSON " + json);
            ejson = gson.fromJson(json, new TypeToken<EnderecoJSON>() {
            }.getType());
            System.out.println("Lat " + ejson.getLat());
            System.out.println("Rua " + ejson.getRua());
            System.out.println("Bairro " + ejson.getBairro());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Double getDistancia(String lat1, String lat2, String lon1, String lon2) {
        Double distancia = 0.0;
        try {
            Client c = new Client();
            Gson gson = new Gson();
            WebResource wr;
            String json = "";
            String url = "https://testenodered99.mybluemix.net/distancia?lat1=" + lat1 + "&lat2=" + lat2 + "&lon1=" + lon1 + "&lon2=" + lon2;
            URI uri = URI.create(url);
            wr = c.resource(uri);
            System.out.println("tentando get distancia");
            wr.header("Content-Type", "application/json;charset=UTF-8");
            json = wr.get(String.class);
            String d = gson.fromJson(json, String.class);
            System.out.println("Distancia " + d);
            distancia = Double.parseDouble(d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return distancia;
    }

    private boolean validaCPF(String cpf) {
        String cpf_ = "";
        System.out.println(cpf);
        for (int i = 0; i < cpf.length(); i++) {
            if (cpf.charAt(i) == '1' || cpf.charAt(i) == '2' || cpf.charAt(i) == '3' || cpf.charAt(i) == '4' || cpf.charAt(i) == '5'
                    || cpf.charAt(i) == '6' || cpf.charAt(i) == '7' || cpf.charAt(i) == '8' || cpf.charAt(i) == '9' || cpf.charAt(i) == '0') {
                cpf_ += cpf.charAt(i);
            }
        }
        System.out.println(cpf_);
        if (cpf_.length() > 11 || cpf_.equals("11111111111") || cpf_.equals("22222222222")
                || cpf_.equals("33333333333") || cpf_.equals("44444444444") || cpf_.equals("55555555555")
                || cpf_.equals("66666666666") || cpf_.equals("77777777777") || cpf_.equals("88888888888")
                || cpf_.equals("99999999999") || cpf_.equals("00000000000")) {
            return false;
        }
        int multi = 10;
        int soma = 0;
        for (int i = 0; i < cpf_.length() - 2; i++) {
            String n = "" + cpf_.charAt(i);
            soma += (Integer.parseInt(n) * multi--);
        }
        int d1 = 11 - (soma % 11);
        if (d1 > 9) {
            d1 = 0;
        }
        String TXTd1 = "" + cpf_.charAt(cpf_.length() - 2);

        if (d1 != Integer.parseInt(TXTd1)) {
            return false;
        }
        soma = 0;
        multi = 11;
        for (int i = 0; i < cpf_.length() - 1; i++) {
            String n = "" + cpf_.charAt(i);
            soma += (Integer.parseInt(n) * multi--);
        }
        int d2 = 11 - (soma % 11);
        if (d2 > 9) {
            d2 = 0;
        }
        String TXTd2 = "" + cpf_.charAt(cpf_.length() - 1);
        if (d2 != Integer.parseInt(TXTd2)) {
            return false;
        }
        return true;
    }

}
