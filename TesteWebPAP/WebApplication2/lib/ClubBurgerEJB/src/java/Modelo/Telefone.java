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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ingo
 */
@Entity
@Table(name = "TELEFONE")

@NamedQueries({
    @NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t")
    , @NamedQuery(name = "Telefone.findById", query = "SELECT t FROM Telefone t WHERE t.id = :id")
    , @NamedQuery(name = "Telefone.findByTelefone", query = "SELECT t FROM Telefone t WHERE t.telefone = :telefone")
    , @NamedQuery(name = "Telefone.findByDdd", query = "SELECT t FROM Telefone t WHERE t.ddd = :ddd")
    , @NamedQuery(name = "Telefone.findByTempo", query = "SELECT t FROM Telefone t WHERE t.tempo = :tempo")})
@SequenceGenerator(name = "TelefoneSEQ",
        sequenceName = "TEL_SEQ", initialValue = 1,
        allocationSize = 1)
public class Telefone implements Serializable {

   private static final long serialVersionUID = 5246873108746308412L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "TelefoneSEQ")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TELEFONE")
    private int telefone;
    @Basic(optional = false)
    @Column(name = "DDD")
    private short ddd;
    @Basic(optional = false)
    @Column(name = "TEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo;
    @ManyToMany(mappedBy = "telefoneList", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Cliente> clienteList;

    public Telefone() {
    }

    public Telefone(Long id) {
        this.id = id;
    }

    public Telefone(int telefone, short ddd, Date tempo) {
        this.telefone = telefone;
        this.ddd = ddd;
        this.tempo = tempo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public short getDdd() {
        return ddd;
    }

    public void setDdd(short ddd) {
        this.ddd = ddd;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

    
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    } 
}
