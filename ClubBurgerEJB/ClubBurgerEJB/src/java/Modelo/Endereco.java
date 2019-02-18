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
@Table(name = "ENDERECO")
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e")
    , @NamedQuery(name = "Endereco.ProcurarPorId", query = "SELECT e FROM Endereco e WHERE e.id = :id")
    , @NamedQuery(name = "Endereco.ProcurarPorRua", query = "SELECT e FROM Endereco e WHERE e.rua = :rua")
    , @NamedQuery(name = "Endereco.ProcurarPorCidade", query = "SELECT e FROM Endereco e WHERE e.cidade = :cidade")
    , @NamedQuery(name = "Endereco.ProcurarPorEstado", query = "SELECT e FROM Endereco e WHERE e.estado = :estado")})
@SequenceGenerator(name = "EnderecoSEQ",
        sequenceName = "END_SEQ", initialValue = 1,
        allocationSize = 1)
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1548267210571525222L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "EnderecoSEQ")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "RUA")
    private String rua;
    @Basic(optional = false)
    @Column(name = "CIDADE")
    private String cidade;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "NUMERO")
    private int numero;
    @Basic(optional = false)
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Basic(optional = false)
    @Column(name = "TEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo;

    public Endereco() {
    }

    public Endereco(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco(String rua, String cidade, String estado, int numero, String complemento, Date tempo) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.complemento = complemento;
        this.tempo = tempo;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

//    public List<Entrega> getEntregaList() {
//        return entregaList;
//    }
//
//    public void setEntregaList(List<Entrega> entregaList) {
//        this.entregaList = entregaList;
//    }

}
