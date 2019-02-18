/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Telefone;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ingo
 */
public class TelefoneDAO {

    private EntityManager em;

    public TelefoneDAO(EntityManager em) {
        this.em = em;
    }

    public Telefone salvar(Telefone tel) throws Exception {
        if (tel.getId() == null) {
            System.out.println("Salvando novo Endereco " + new Date());
            em.persist(tel);
        } else {
            if (em.contains(tel) == false) {
                if (em.find(Telefone.class, tel.getId()) == null) {
                    throw new Exception("Telefone desconhecido " + new Date());
                }
            }
            tel.setTempo(new Date());
            em.merge(tel);
        }
        Telefone telefone = new Telefone();
        try {
            telefone = em.find(Telefone.class, tel.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (telefone.getId() == null || telefone.getTelefone() == null) {
            throw new Exception("ERRO ao buscar telefone salvo " + new Date());
        }
        return telefone;
    }

    public Telefone buscarPorID(Long id) throws Exception {
        if (id < 0) {
            throw new Exception("Erro ao buscar telefone por id      id<0 " + new Date());
        }
        Telefone ende = new Telefone(id);
        try {
            ende = em.find(Telefone.class, ende.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ende.getTelefone() == null) {
            throw new Exception("Erro ao buscar por id      telefone = null " + new Date());
        }
        return ende;
    }
    
    public List<Telefone> buscarTodos() {
        Query q = em.createNamedQuery("Telefone.findAll");
        List<Telefone> TelefoneLista = new LinkedList();
        try {
            System.out.println("Tentando buscar todos Telefones " + new Date());
            TelefoneLista = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar todos os Telefones " + new Date());
        }
        System.out.println("Telefones consultados com sucesso " + new Date());
        return TelefoneLista;
    }
    
    public List<Telefone> BuscarPorNumeroDeTelefone(Integer numero){
        Query q = em.createNamedQuery("Telefone.BuscarPorTelefone");
        q.setParameter("telefone", numero);
        List<Telefone> TelefoneLista = new LinkedList();
        try {
            System.out.println("Tentando buscar todos Telefones por numero " + new Date());
            TelefoneLista = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar todos os Telefones por numero " + new Date());
        }
        System.out.println("Telefones consultados com sucesso " + new Date());
        return TelefoneLista;
    }
    public void remover(long id) throws Exception{
        if(id < 0){
            throw new Exception("ERRO ao remover telefone id < 0 "+ new Date());
        }
        try{
            Telefone t = em.find(Telefone.class, id);
            if(t == null){
                throw new Exception("ERRO Telefone a ser removido nao foi encontrado "+ new Date());
            }
            em.remove(t);
        }catch(Exception e){
            throw new Exception("ERRO ao remover telefone "+ new Date()+"    "+ e.getMessage());
        }
    }
}
