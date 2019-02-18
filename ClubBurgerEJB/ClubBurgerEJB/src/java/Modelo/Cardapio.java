/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Ingo
 */
@Entity
@Table(name = "CARDAPIO")
@NamedQueries({
    @NamedQuery(name = "Cardapio.BuscarTodos", query = "SELECT c FROM Cardapio c")
    , @NamedQuery(name = "Cardapio.BuscarPorNome", query = "SELECT c FROM Cardapio c WHERE c.nome like :nome")
    , @NamedQuery(name = "Cardapio.BuscarPorDescricao", query = "SELECT c FROM Cardapio c WHERE c.descricao like :descricao")})
@SequenceGenerator(name = "CARDAPIO",
        sequenceName = "CARD_SEQ", initialValue = 1,
        allocationSize = 1)
public class Cardapio implements Serializable {

    private static final long serialVersionUID = 2421242021238724129L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "CARDAPIO")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "IMG")
    private String img;

    @JoinTable(name = "ProdutoCardapio", joinColumns = {
        @JoinColumn(name = "ID_Cardapio", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_Produto", referencedColumnName = "ID")})
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<Produto> produtoList;

    public Long getId() {
        return id;
    }

    public Cardapio() {
    }

    public Cardapio(String img,String nome, String descricao) {
        this.img = img;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Cardapio(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
