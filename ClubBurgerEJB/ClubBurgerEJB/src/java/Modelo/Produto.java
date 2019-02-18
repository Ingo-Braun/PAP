/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ingo
 */
@Entity
@Table(name = "PRODUTO")
@NamedQueries({
    @NamedQuery(name = "Produto.BuscarTodos", query = "SELECT p FROM Produto p")
    , @NamedQuery(name = "Produto.BuscarPorNome", query = "SELECT p FROM Produto p WHERE p.nome like :nome")
    , @NamedQuery(name = "Produto.BuscarPorDescricao", query = "SELECT p FROM Produto p WHERE p.descricao like :descricao")})
@SequenceGenerator(name = "ProdutoSEQ",
        sequenceName = "PROD_SEQ", initialValue = 1,
        allocationSize = 1)
public class Produto implements Serializable {

   private static final long serialVersionUID = 2132498631368746833L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ProdutoSEQ")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "PRECO")
    private Double preco;
    @Basic(optional = false)
    @Column(name = "TEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo;
//    @Basic(optional = false)
//    @Column(name="IMG")
//    private String img; 
//    
    @JoinColumn(name = "ID_ESTOQUE", referencedColumnName = "ID")
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Estoque estoque;

    public Produto() {
    }

    public Produto(Long id) {
        this.id = id;
    }

    public Produto(String nome, String descricao, Double preco, Date tempo) {
        
//        this.img = img;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tempo = tempo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }


    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}
    