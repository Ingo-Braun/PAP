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
@Table(name = "VENDA")
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v")
    , @NamedQuery(name = "Venda.findById", query = "SELECT v FROM Venda v WHERE v.id = :id")
    , @NamedQuery(name = "Venda.findByQtd", query = "SELECT v FROM Venda v WHERE v.qtd = :qtd")
    , @NamedQuery(name = "Venda.findByTempo", query = "SELECT v FROM Venda v WHERE v.tempo = :tempo")})
@SequenceGenerator(name = "VendaSEQ",
        sequenceName = "VEND_SEQ", initialValue = 1,
        allocationSize = 1)
public class Venda implements Serializable {

    private static final long serialVersionUID = 5463216762791138918L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "VendaSEQ")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "QTD")
    private short qtd;
    @Basic(optional = false)
    @Column(name = "TEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo;
    @ManyToMany(mappedBy = "vendaList", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Pedido> pedidoList;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Produto idProduto;

    public Venda() {
    }

    public Venda(Long id) {
        this.id = id;
    }

    public Venda( short qtd, Date tempo) {
        
        this.qtd = qtd;
        this.tempo = tempo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getQtd() {
        return qtd;
    }

    public void setQtd(short qtd) {
        this.qtd = qtd;
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

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }
}
