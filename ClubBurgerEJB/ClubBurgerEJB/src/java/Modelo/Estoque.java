/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ESTOQUE")
@NamedQueries({
    @NamedQuery(name = "Estoque.BuscarTodos", query = "SELECT e FROM Estoque e")})
@SequenceGenerator(name = "ESTOQUE_SEQ",
        sequenceName = "EST_SEQ", initialValue = 1,
        allocationSize = 1)
public class Estoque implements Serializable {

    private static final long serialVersionUID = 1331234643746303845L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ESTOQUE_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
    private int quantidade;
    @Basic(optional = false)
    @Column(name = "TEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo;
   

    public Estoque() {
    }

    public Estoque(Long id) {
        this.id = id;
    }

    public Estoque(int quantidade, Date tempo) {
        this.quantidade = quantidade;
        this.tempo = tempo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

}
