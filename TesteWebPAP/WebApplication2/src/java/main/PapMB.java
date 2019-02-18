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
    private Pedido pedido = new Pedido();

    @PostConstruct
    public void iniciar() {
        List<Cardapio> lista = new ArrayList();
        SessionContext.getInstance().setAttribute("Carrinho", lista);
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    public boolean login() {
        boolean resposta;
        List<Cliente> Clientes = new ArrayList();
        System.out.println("Tentativa de login " + new Date());
        if (cli.getCpf() != null) {
            System.out.println("INFO-Buscando cpf " + cli.getCpf() + " " + new Date());
            try {
                Clientes = clienteR.BuscarPorCpf(cli.getCpf());
            } catch (Exception ex) {
                Logger.getLogger(PapMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cli.getEmail() != null) {
            System.out.println("INFO-Buscando email " + cli.getEmail() + " " + new Date());
            try {
                Clientes = clienteR.BuscarPorEmail(cli.getEmail());
            } catch (Exception ex) {
                Logger.getLogger(PapMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("ERRO-Email ou cpf nullo " + new Date());
            return false;
        }
        if (Clientes.size() > 0) {
            System.out.println("INFO-Verificando Resultados " + new Date());
            if (Clientes.get(0).getCpf().equals(cli.getCpf()) || Clientes.get(0).getEmail().equals(cli.getEmail())) {
                System.out.println("INFO-Verificando senha " + new Date());
                if (Clientes.get(0).verificaSenha(cli.getSenha())) {
                    resposta = true;
                    SessionContext.getInstance().setAttribute("Logado", Clientes.get(0));
                    System.out.println("INFO-Login efetuado com sucesso para o cpf " + Clientes.get(0).getCpf() + " " + new Date());
                } else {
                    System.out.println("ERRO-Senha incorreta " + new Date());
                    return false;
                }
            } else {
                System.out.println("ERRO-Resultados incoerentes " + new Date());
                return false;
            }
        } else {
            System.out.println("INFO-CLiente nao encontrado " + new Date());
            return false;
        }
        limparCliente();
        return resposta;
    }

    public boolean verificaLogado() {
        boolean resposta = false;
        if (SessionContext.getInstance().getAttribute("Logado") != null) {
            resposta = true;
        }
        return resposta;
    }

    public boolean cadastrar() {
        boolean resposta = false;
        if (cli.getCpf() != null && cli.getEmail() != null && cli.getSenha() != null && cli.getNome() != null) {
            if (tel.getDdd() != null && tel.getTelefone() != null) {
                if (ejson.getLat() != null && ejson.getLon() != null) {
                    try {
                        if (clienteR.BuscarPorEmail(cli.getEmail()).isEmpty() && clienteR.BuscarPorCpf(cli.getCpf()).isEmpty()) {
                            Cliente novoCliente = cli;
                            List<Telefone> TelList = new ArrayList();
                            TelList.add(tel);
                            novoCliente.setTelefoneList(TelList);
                            List<Endereco> EnderecoList = new ArrayList();
                            EnderecoList.add(new Endereco(ejson.getRua(), ejson.getCidade(), ejson.getEstado(), Integer.parseInt(ejson.getN()), complemento, new Date()));
                            novoCliente.setEnderecoList(EnderecoList);
                            novoCliente = clienteR.Salvar(cli);
                            if (novoCliente.getId() != null) {
                                resposta = true;
                                SessionContext.getInstance().setAttribute("Logado", novoCliente);
                                System.out.println("INFO-Novo cliente Cadastrado id: " + novoCliente.getId() + " " + new Date());
                            } else {
                                System.out.println("FATAL-ERRO ao salvar novo cliente " + new Date());
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(PapMB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

        return resposta;
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

        //
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
                                foiAtualizado = false;
                                if (VendaList.get(b).getIdProduto() == ProdutoList.get(a)) {
                                    Venda vnd = VendaList.get(b);
                                    vnd.setQtd(vnd.getQtd() + 1);
                                    VendaList.set(b, vnd);
                                    foiAtualizado = true;
                                }
                            }
                            if (foiAtualizado == false) {
                                Venda novaVenda = new Venda();
                                novaVenda.setIdProduto(ProdutoList.get(a));
                                novaVenda.setQtd(1);
                                VendaList.add(novaVenda);
                            }
                        }

                    }
                    // 
                    if (pedido.getFormaentrega() != null && pedido.getPgto() != null) {
                        try {
                            for (int i = 0; i < VendaList.size(); i++) {
                                VendaList.set(i, VendaR.salvar(VendaList.get(i)));
                            }
                            pedido.setVendaList(VendaList);
                            pedido.setStatus(Short.parseShort("1"));//0 = não processado 1 = processado 2 = entregando 3 = entregue 4 = cancelado pelo Cliente 5 = cancelado por outro motivo 
                            Entrega novaEntrega = new Entrega();
                            novaEntrega.setEndereco(new Endereco(ejson.getRua(), ejson.getCidade(), ejson.getEstado(), Integer.parseInt(ejson.getN()), complemento, new Date()));
                            pedido = PedidoR.salvar(pedido);
                            novaEntrega.setPedido(pedido);
                            novaEntrega.setDataentrega(new Date());
                            EntregaR.salvar(novaEntrega);
                        } catch (Exception ex) {
                            Logger.getLogger(PapMB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        System.out.println("INFO-Fim da thread " + new Date());
        return "";
    }

    public List<Cardapio> buscarCardapio() throws Exception {
        return CardapioR.BuscarTodos();
    }
    
    public List<Pgto> buscarFormasDePgto() throws Exception{
        return PgtoR.BuscarTodos();
    }
    public List<Fentrega> BuscarFormasDeEntrega() throws Exception{
        return FentregaR.BuscarTodos();
    }

}
