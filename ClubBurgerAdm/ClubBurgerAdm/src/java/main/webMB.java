/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Modelo.*;
import ejb.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ingo
 */
@ManagedBean
@SessionScoped
public class webMB {

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

    public Cliente cliente = new Cliente();

    public String telefone;

    public Endereco endereco = new Endereco();

    public Produto produto = new Produto();

    public Pgto pgto = new Pgto();

    public Fentrega fentrega = new Fentrega();

    public Cardapio cardapio = new Cardapio();

    public Cliente ClienteSalvo = new Cliente();
    
    public String ddd;
    
    public String numero;
    
    public String preco;

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public Cliente getClienteSalvo() {
        return ClienteSalvo;
    }

    public void setClienteSalvo(Cliente ClienteSalvo) {
        this.ClienteSalvo = ClienteSalvo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente.getId() != null) {
            System.out.println("Cliente setado com id " + cliente.getId());
        }
        this.cliente = cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pgto getPgto() {
        return pgto;
    }

    public void setPgto(Pgto pgto) {
        this.pgto = pgto;
    }

    public Fentrega getFentrega() {
        return fentrega;
    }

    public void setFentrega(Fentrega fentrega) {
        this.fentrega = fentrega;
    }

    public List<Cardapio> buscarCardapio() {
        List<Cardapio> lista = new ArrayList();
        if (cardapio.getNome() == null) {
            try {
                lista = CardapioR.BuscarTodos();
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                lista = CardapioR.BuscarPorNome(cardapio.getNome());
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    public List<Cliente> BuscarClientes() {
        List<Cliente> clientes = new ArrayList();
        System.out.println("Buscando Clientes");
        if (cliente.getCpf() != null) {
            try {
                clientes = clienteR.BuscarPorCpf(cliente.getCpf());
                System.out.println("Buscando por cpf");
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cliente.getEmail() != null) {
            try {
                clientes = clienteR.BuscarPorEmail(cliente.getEmail());
                System.out.println("Buscando por email");
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cliente.getId() != null) {
            try {
                System.out.println("Buscando por id");
                clientes.add(clienteR.BuscarPorId(cliente.getId()));
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                System.out.println("Buscando todos ");
                clientes = clienteR.getAllCliente();
                System.out.println(clientes.size() + " clientes encontrados");
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cliente = new Cliente();
        return clientes;
    }

    public List<Produto> BuscarProdutos() {
        List<Produto> produtos = new ArrayList();
        if (produto.getId() != null) {
            try {
                produtos.add(ProdutoR.BuscarPorId(produto.getId()));
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (produto.getNome() != null) {
            try {
                produtos = ProdutoR.BuscarPorNome(produto.getNome());
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            try {
                produtos = ProdutoR.BuscarTodos();
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return produtos;
    }

    public List<Pgto> buscarFormasDePagamento() {
        List<Pgto> pgtos = new ArrayList();
        if (pgto.getId() != null) {
            try {
                pgtos.add(PgtoR.BuscarPorId(pgto.getId()));
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (pgto.getForma() != null) {
            try {
                pgtos = PgtoR.BuscarPorForma(pgto.getForma());
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (pgto.getNome() != null) {
            try {
                pgtos = PgtoR.BuscarPorNome(pgto.getNome());
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (pgto.getDescricao() != null) {
            try {
                pgtos = PgtoR.BuscarPorDescricao(pgto.getDescricao());
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pgtos;
    }

    public List<Fentrega> BuscarFormasDeEntrega() {
        List<Fentrega> fentregas = new ArrayList();
        if (fentrega.getId() != null) {
            try {
                fentregas.add(FentregaR.BuscarPorId(fentrega.getId()));
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (fentrega.getForma() != null) {
            try {
                fentregas = FentregaR.BuscarPorForma(fentrega.getForma());
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (fentrega.getNome() != null) {
            try {
                fentregas = FentregaR.BuscarPorNome(fentrega.getNome());
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (fentrega.getDescricao() != null) {
            try {
                fentregas = FentregaR.BuscarPorDescricao(fentrega.getDescricao());
            } catch (Exception ex) {
                Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fentregas;
    }

    public String logout() {
        return "index";
    }

    public String SalvarNovoCliente() {
        System.out.println("salvando cliente");
        try {
            Telefone tel = new Telefone();
            tel.setTelefone(Integer.parseInt(telefone));
            tel.setDdd(Short.parseShort(ddd));
            endereco.setTempo(new Date());
            tel.setTempo(new Date());
            List<Telefone> TelefoneList = new ArrayList();
            List<Endereco> EnderecoList = new ArrayList();
            EnderecoList.add(EnderecoR.salvar(endereco));
            TelefoneList.add(TelefoneR.salvar(tel));
            cliente.setEnderecoList(EnderecoList);
            cliente.setTelefoneList(TelefoneList);
            cliente.setDatacria(new Date());
            clienteR.Salvar(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "Your message: "));
        cliente = new Cliente();
        return "";
    }
    
    public String savarNovoProduto(){
        produto.setTempo(new Date());
        try {
            produto.setPreco(Double.parseDouble(preco));
            ProdutoR.salvar(produto);
        } catch (Exception ex) {
            Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        produto = new Produto();
        return "";
    }
    
    public String salvarCliente(){
        System.out.println("salvando cliente");
        if(ClienteSalvo.getId()!= null){
            System.out.println(cliente.getId());
        }
        try {
            clienteR.Salvar(cliente);
        } catch (Exception ex) {
            Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        cliente = new Cliente();
        return "";
    }
    
    
    public String salvarProduto(){
        System.out.println("salvando produto");
        if(ClienteSalvo.getId()!= null){
            System.out.println(produto.getId());
        }
        try {
            ProdutoR.salvar(produto);
        } catch (Exception ex) {
            Logger.getLogger(webMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        produto = new Produto();
        return "";
    }

    public List<String> listaTeste = new ArrayList();

    public List<String> getListaTeste() {
        return listaTeste;
    }

    public String teste() {
        listaTeste.add("teste " + listaTeste.size());
        System.out.println("teste");
        return listaTeste.get(listaTeste.size() - 1);
    }

    public void red() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/main.xhtml");
    }

//    
//    <p:commandLink update=":gro:growl" title="Editar Cliente">
//                                <h:outputText styleClass="fa-pencil" style="margin:0 auto;font-family: FontAwesome;" />
//                                <f:setPropertyActionListener value="#{Cliente}" target="#{webMB.clienteSalvo}" />
//                            </p:commandLink>
}
