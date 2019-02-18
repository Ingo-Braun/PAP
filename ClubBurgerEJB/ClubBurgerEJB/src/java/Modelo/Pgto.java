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
@Table(name = "PGTO")
@NamedQueries({
    @NamedQuery(name = "Pgto.BuscarTodos", query = "SELECT p FROM Pgto p")
    , @NamedQuery(name = "Pgto.BuscarPorNome", query = "SELECT p FROM Pgto p WHERE p.nome like :nome")
    , @NamedQuery(name = "Pgto.BuscarPorForma", query = "SELECT p FROM Pgto p WHERE p.forma like :forma")
    , @NamedQuery(name = "Pgto.BuscarPorDescricao", query = "SELECT p FROM Pgto p WHERE p.descricao like :descricao")})
@SequenceGenerator(name = "FPagamento_SEQ",
        sequenceName = "PGTO_SEQ", initialValue = 1,
        allocationSize = 1)
public class Pgto implements Serializable {

    private static final long serialVersionUID = 2021251413163894301L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "FPagamento_SEQ")
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
    

    public Pgto() {
    }

    public Pgto(Long id) {
        this.id = id;
    }

    public Pgto( String nome, String forma, String descricao, Date tempo) {
        
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
}
