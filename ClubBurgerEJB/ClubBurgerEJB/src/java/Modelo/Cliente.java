/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ingo
 */
@Entity
@Table(name = "CLIENTE")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.BuscarPorNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome")
    , @NamedQuery(name = "Cliente.BuscarPorSobrenome", query = "SELECT c FROM Cliente c WHERE c.sobrenome = :sobrenome")
    , @NamedQuery(name = "Cliente.BuscarPorEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email")
    , @NamedQuery(name = "Cliente.BuscarPorCpf", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf")
    , @NamedQuery(name = "Cliente.BuscarPorDatacria", query = "SELECT c FROM Cliente c WHERE c.datacria = :datacria")
    , @NamedQuery(name = "Cliente.BuscarPorTempo", query = "SELECT c FROM Cliente c WHERE c.tempo = :tempo")})
@SequenceGenerator(name = "CLIENTE",
        sequenceName = "CLI_SEQ", initialValue = 1,
        allocationSize = 1)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1246213625687519943L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "CLIENTE")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "SOBRENOME")
    private String sobrenome;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "SENHA")
    private String senha;
    @Basic(optional = false)
    @Column(name = "CPF")
    private String cpf;
    //
    @Basic(optional = false)
    @Column(name = "DATACRIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacria;
    //
    @Basic(optional = false)
    @Column(name = "TEMPO")
    //
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo;
    //
    @JoinTable(name = "CLIENTEPEDIDO", joinColumns = {
        @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID")})
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<Pedido> pedidoList;

    @JoinTable(name = "ENDERECOCLIENTE", joinColumns = {
        @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID")})
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<Endereco> enderecoList;
//    
    @JoinTable(name = "TELEFONECLIENTE", joinColumns = {
        @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_TELEFONE", referencedColumnName = "ID")})
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<Telefone> telefoneList;

    public Cliente() {
    }

    public Cliente(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente(String nome, String sobrenome, String email, String senha, String cpf, Date datacria) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email.toLowerCase();
        try {
            this.senha = toSHA256(senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cpf = cpf;
        this.datacria = datacria;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        try {
            this.senha = toSHA256(senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verificaSenha(String SenhaLogin) {
        boolean resposta = false;
        try {
            if(senha.equals(SenhaLogin)){
                resposta = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resposta;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDatacria() {
        return datacria;
    }

    public void setDatacria(Date datacria) {
        this.datacria = datacria;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    private String toSHA256(String s) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("SHA-256");
        m.update(s.getBytes(), 0, s.length());
        String resposta = "";
        resposta = resposta + new BigInteger(1, m.digest()).toString(16);
        return resposta;
    }

}
