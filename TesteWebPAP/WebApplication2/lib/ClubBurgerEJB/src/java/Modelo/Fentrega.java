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
@Table(name = "FENTREGA")

@NamedQueries({
    @NamedQuery(name = "Fentrega.findAll", query = "SELECT f FROM Fentrega f")
    , @NamedQuery(name = "Fentrega.findById", query = "SELECT f FROM Fentrega f WHERE f.id = :id")
    , @NamedQuery(name = "Fentrega.findByNome", query = "SELECT f FROM Fentrega f WHERE f.nome = :nome")
    , @NamedQuery(name = "Fentrega.findByForma", query = "SELECT f FROM Fentrega f WHERE f.forma = :forma")
    , @NamedQuery(name = "Fentrega.findByDescricao", query = "SELECT f FROM Fentrega f WHERE f.descricao = :descricao")
    , @NamedQuery(name = "Fentrega.findByTempo", query = "SELECT f FROM Fentrega f WHERE f.tempo = :tempo")})
@SequenceGenerator(name = "Fentrega_SEQ",
        sequenceName = "FEn_SEQ", initialValue = 1,
        allocationSize = 1)
public class Fentrega implements Serializable {

    private static final long serialVersionUID = 5431638435134432140L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Fentrega_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "FORMA")
    private String forma;
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "TEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formaentrega", fetch = FetchType.EAGER)
    private List<Pedido> pedidoList;

    public Fentrega() {
    }

    public Fentrega(Long id) {
        this.id = id;
    }

    public Fentrega( String nome, String forma, String descricao, Date tempo) {
        
        this.nome = nome;
        this.forma = forma;
        this.descricao = descricao;
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

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }    
}

