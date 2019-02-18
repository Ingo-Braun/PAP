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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ingo
 */
@Entity
@Table(name = "ENTREGA")
@NamedQueries({
    @NamedQuery(name = "Entrega.findAll", query = "SELECT e FROM Entrega e")
    , @NamedQuery(name = "Entrega.findById", query = "SELECT e FROM Entrega e WHERE e.id = :id")
    , @NamedQuery(name = "Entrega.findByDataentrega", query = "SELECT e FROM Entrega e WHERE e.dataentrega = :dataentrega")
    , @NamedQuery(name = "Entrega.findByTempo", query = "SELECT e FROM Entrega e WHERE e.tempo = :tempo")})
@SequenceGenerator(name = "EntregaSEQ",
        sequenceName = "ENTR_SEQ", initialValue = 1,
        allocationSize = 1)
public class Entrega implements Serializable {

    private static final long serialVersionUID = 2542482731237134101L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "EntregaSEQ")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "DATAENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataentrega;
    @Basic(optional = false)
    @Column(name = "TEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo;
    @JoinTable(name = "PEDIDOENTREGA", joinColumns = {
        @JoinColumn(name = "ID_ENTREGA", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private List<Pedido> pedidoList;
    @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Endereco idEndereco;

    public Entrega() {
    }

    public Entrega(Long id) {
        this.id = id;
    }

    public Entrega(Long id, Date dataentrega, Date tempo) {
        this.id = id;
        this.dataentrega = dataentrega;
        this.tempo = tempo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataentrega() {
        return dataentrega;
    }

    public void setDataentrega(Date dataentrega) {
        this.dataentrega = dataentrega;
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

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        this.idEndereco = idEndereco;
    }


}
