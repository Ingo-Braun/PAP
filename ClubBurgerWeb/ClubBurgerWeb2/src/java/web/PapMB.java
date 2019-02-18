/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Modelo.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ejb.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ingo
 */
@ManagedBean
@SessionScoped
public class PapMB {

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

    private EnderecoJSON ejson = new EnderecoJSON();
    private String telefone;
    private Telefone tel = new Telefone();
    private Cardapio cardapio = new Cardapio();
    private Cliente cli = new Cliente();
    private String complemento;
    private String ddd;
    private Fentrega fentrega = new Fentrega();
    private Pgto pgto = new Pgto();

    @PostConstruct
    public void iniciar() {
        List<Cardapio> lista = new ArrayList();
        SessionContext.getInstance().setAttribute("Carrinho", lista);
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cliente getCli() {
        return cli;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    private void limparCliente() {
        this.cli = new Cliente();
    }

    private void limparCardapio() {
        this.cardapio = new Cardapio();
    }

    private void limparEndereco() {
        this.ejson = new EnderecoJSON();
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public Telefone getTel() {
        return tel;
    }

    public void setTel(Telefone tel) {
        this.tel = tel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoJSON getEjson() {
        return ejson;
    }

    public void setEjson(EnderecoJSON ejson) {
        this.ejson = ejson;
    }

    public Fentrega getFentrega() {
        return fentrega;
    }

    public void setFentrega(Fentrega fentrega) {
        this.fentrega = fentrega;
    }

    public Pgto getPgto() {
        return pgto;
    }

    public void setPgto(Pgto pgto) {
        this.pgto = pgto;
    }

    public void adicionarProduto() {
        List<Cardapio> lista = new ArrayList();
        lista = (List<Cardapio>) SessionContext.getInstance().getAttribute("Carrinho");
        lista.add(cardapio);
        SessionContext.getInstance().setAttribute("Carrinho", lista);
        limparCardapio();
    }

    public void removerProduto() {
        List<Cardapio> lista = new ArrayList();
        lista = (List<Cardapio>) SessionContext.getInstance().getAttribute("Carrinho");
        int index = lista.indexOf(cardapio);
        if (index != -1) {
            lista.remove(index);
        }
        SessionContext.getInstance().setAttribute("Carrinho", lista);
        limparCardapio();
    }

    public void limparCarrionho() {
        List<Cardapio> lista = new ArrayList();
        SessionContext.getInstance().setAttribute("Carrinho", lista);
    }

    public List<Cardapio> getCarrinho() {
        return (List<Cardapio>) SessionContext.getInstance().getAttribute("Carrinho");
    }

    public void login() {
        boolean resposta = false;
        Cliente cliente = cli;
        List<Cliente> Clientes = new ArrayList();

        System.out.println(
                "Tentativa de login " + new Date());
        if (cli.getCpf()
                != null || !cli.getCpf().equals("")) {
            System.out.println("INFO-Buscando cpf " + cli.getCpf() + " " + new Date());
            try {
                Clientes = clienteR.BuscarPorCpf(cli.getCpf());
            } catch (Exception ex) {
                Logger.getLogger(PapMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (cli.getEmail()
                != null || !cli.getEmail().equals("")) {
            System.out.println("INFO-Buscando email " + cli.getEmail() + " " + new Date());
            try {
                Clientes = clienteR.BuscarPorEmail(cli.getEmail());
            } catch (Exception ex) {
                Logger.getLogger(PapMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("ERRO-Email ou cpf nullo " + new Date());
            resposta = false;
        }

        if (Clientes.size()
                > 0) {
            System.out.println("INFO-Verificando Resultados " + new Date());
            if (Clientes.get(0).getCpf().equals(cli.getCpf()) || Clientes.get(0).getEmail().equals(cli.getEmail())) {
                System.out.println("INFO-Verificando senha " + new Date());
                if (Clientes.get(0).verificaSenha(cli.getSenha())) {
                    resposta = true;
                    SessionContext.getInstance().setAttribute("Logado", Clientes.get(0));
                    System.out.println("INFO-Login efetuado com sucesso para o cpf " + Clientes.get(0).getCpf() + " " + new Date());
                } else {
                    System.out.println("ERRO-Senha incorreta " + new Date());
                    resposta = false;
                }
            } else {
                System.out.println("ERRO-Resultados incoerentes " + new Date());
                resposta = false;
            }
        } else {
            System.out.println("INFO-CLiente nao encontrado " + new Date());
            resposta = false;
        }
        limparCliente();

    }

    public boolean verificaLogado() {
        boolean resposta = false;
        if (SessionContext.getInstance().getAttribute("Logado") != null) {
            resposta = true;
        }
        return resposta;
    }

    public void cadastrar() {
        boolean resposta = false;
        try {
            List<Cliente> cliente = clienteR.BuscarPorEmail(cli.getEmail());
            if (cliente.isEmpty()) {
                Cliente novoCliente = cli;
                System.out.println("cadastrando um novo cliente " + cli.getNome() + " " + cli.getCpf() + " "
                        + cli.getEmail() + " " + cli.getSobrenome() + " " + tel.getDdd() + " " + tel.getTelefone());
                List<Telefone> TelList = new ArrayList();
                TelList.add(TelefoneR.salvar(new Telefone(Integer.parseInt(telefone), Short.parseShort(ddd), new Date())));
                System.out.println("Telefone salvo com id " + TelList.get(0).getId());
                novoCliente.setTelefoneList(TelList);
                List<Endereco> EnderecoList = new ArrayList();
                EnderecoList.add(EnderecoR.salvar(new Endereco(ejson.getRua(), ejson.getCidade(), ejson.getEstado(), Integer.parseInt(ejson.getN()), complemento, new Date())));
                novoCliente.setEnderecoList(EnderecoList);
                novoCliente.setDatacria(new Date());
                novoCliente = clienteR.Salvar(cli);
                if (novoCliente.getId() != null) {
                    resposta = true;
                    SessionContext.getInstance().setAttribute("Logado", novoCliente);
                    System.out.println("INFO-Novo cliente Cadastrado id: " + novoCliente.getId() + " " + new Date());
                } else {
                    System.out.println("FATAL-ERROR ao salvar novo cliente " + new Date());

                }
            } else {
                System.out.println("deu ruim email ja cadastrado ");
            }
        } catch (Exception ex) {
            Logger.getLogger(PapMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() {
        SessionContext.getInstance().setAttribute("Logado", null);
    }

    public EnderecoJSON buscarDadosEndereco(EnderecoJSON e) {
        Client c = new Client();
        Gson gson = new Gson();
        // R. Alfeu Taváres, 149 - Rudge Ramos, São Bernardo do Campo - SP
        WebResource wr;
        String json = "";
        try {
            URI uri = URI.create(e.getLink());
            wr = c.resource(uri);
            System.out.println("tentando get Local");
            wr.header("Content-Type", "application/json;charset=UTF-8");
            json = wr.get(String.class);
            System.out.println("JSON " + json);
            e = gson.fromJson(json, new TypeToken<EnderecoJSON>() {
            }.getType());
            System.out.println("Lat " + ejson.getLat());
            System.out.println("Rua " + ejson.getRua());
            System.out.println("Bairro " + ejson.getBairro());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public String Comprar() {
        if (SessionContext.getInstance().getAttribute("Logado") != null) {
            Cliente cliente = (Cliente) SessionContext.getInstance().getAttribute("Logado");
            if (cliente.getSenha() != null) {
                List<Cardapio> Carrinho = (List<Cardapio>) SessionContext.getInstance().getAttribute("Carrinho");
                if (Carrinho.isEmpty() == false) {
                    List<Venda> VendaList = new ArrayList();
                    for (int i = 0; i < Carrinho.size(); i++) {
                        List<Produto> ProdutoList = Carrinho.get(i).getProdutoList();
                        for (int a = 0; a < ProdutoList.size(); a++) {
                            boolean foiAtualizado = false;
                            //atualiza quantidade de itens vendidos
                            for (int b = 0; b < VendaList.size(); b++) {
                                if (VendaList.get(b).getIdProduto() == ProdutoList.get(a)) {
                                    Venda vnd = VendaList.get(b);
                                    vnd.setQtd(vnd.getQtd() + 1);
                                    VendaList.set(b, vnd);
                                    foiAtualizado = true;
                                }
                            }
                            if (foiAtualizado == false) {
                                Venda novaVenda = new Venda();
                            }

                        }
                    }
                    Pedido p = new Pedido(Short.parseShort("1"), Short.parseShort("1"), new Date());
                    p.setFormaentrega(fentrega);
                    p.setPgto(pgto);
                    p.setVendaList(VendaList);
                    try {
                        p = PedidoR.salvar(p);
                        List<Pedido> pedidoList = cli.getPedidoList();
                        pedidoList.add(p);
                        cliente.setPedidoList(pedidoList);
                        cliente = clienteR.Salvar(cliente);
                        SessionContext.getInstance().setAttribute("Logado", cliente);
                        this.cli = cliente;
                    } catch (Exception ex) {
                        Logger.getLogger(PapMB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    limparCarrionho();
                }
            }
        }
        return "";
    }

    public boolean validaCPF(String cpf) {
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

    private Double getDistancia(String lat1, String lat2, String lon1, String lon2) {
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
    
    public List<Cardapio> buscarCardapio(){
        List<Cardapio> cardapioList = new ArrayList();
        try {
            cardapioList = CardapioR.BuscarTodos();
        } catch (Exception ex) {
            Logger.getLogger(PapMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cardapioList;
    }
}
