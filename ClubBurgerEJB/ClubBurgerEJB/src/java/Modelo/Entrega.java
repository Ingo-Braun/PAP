/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
@Table(name = "ENTREGA")
@NamedQueries({
    @NamedQuery(name = "Entrega.BuscarTodos", query = "SELECT e FROM Entrega e")
    , @NamedQuery(name = "Entrega.BuscarPorDataentrega", query = "SELECT e FROM Entrega e WHERE e.dataentrega = :dataentrega")})
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

    @JoinColumn(name = "ID_Pedido", referencedColumnName = "ID")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Pedido pedido;

    @JoinColumn(name = "ID_Endereco", referencedColumnName = "ID")
    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Endereco endereco;

    public Entrega() {
    }

    public Entrega(Long id) {
        this.id = id;
    }

    public Entrega(Date dataentrega, Date tempo) {
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
