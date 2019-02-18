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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "PEDIDO")
@NamedQueries({
    @NamedQuery(name = "Pedido.BuscarTodos", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.BuscarPorStatus", query = "SELECT p FROM Pedido p WHERE p.status = :status")})
@SequenceGenerator(name = "PedidoSEQ",
        sequenceName = "PED_SEQ", initialValue = 1,
        allocationSize = 1)
public class Pedido implements Serializable {

   private static final long serialVersionUID = 8356431387313486373L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "PedidoSEQ")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "QTDITEMS")
    private short qtditems;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private short status;
    @Basic(optional = false)
    @Column(name = "TEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo;
    
    @JoinTable(name = "VENDAPEDIDO", joinColumns = {
        @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_VENDA", referencedColumnName = "ID")})
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Venda> vendaList;
    
    
    @JoinColumn(name = "FORMAENTREGA", referencedColumnName = "ID")
    @OneToOne(optional = false, fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private Fentrega formaentrega;
    
    @JoinColumn(name = "PGTO", referencedColumnName = "ID")
    @OneToOne(optional = false, fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private Pgto pgto;

    public Pedido() {
    }
    
    public Pedido(short qtditems, short status, Date tempo) {
        this.qtditems = qtditems;
        this.status = status;
        this.tempo = tempo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getQtditems() {
        return qtditems;
    }

    public void setQtditems(short qtditems) {
        this.qtditems = qtditems;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

    
    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }

    public Fentrega getFormaentrega() {
        return formaentrega;
    }

    public void setFormaentrega(Fentrega formaentrega) {
        this.formaentrega = formaentrega;
    }

    public Pgto getPgto() {
        return pgto;
    }

    public void setPgto(Pgto pgto) {
        this.pgto = pgto;
    }    
}
