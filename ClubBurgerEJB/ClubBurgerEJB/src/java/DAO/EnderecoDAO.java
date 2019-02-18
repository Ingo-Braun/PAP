/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Endereco;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author Ingo
 */
public class EnderecoDAO {

    private EntityManager em;

    public EnderecoDAO(EntityManager em) {
        this.em = em;
    }

    public Endereco salvar(Endereco en) throws Exception {
        if (en.getId() == null) {
            System.out.println("Salvando novo Endereco " + new Date());
            em.persist(en);
        } else {
            if (em.contains(en) == false) {
                if (em.find(Endereco.class, en) == null) {
                    throw new Exception("Endereco desconhecido " + new Date());
                }
            }
            en.setTempo(new Date());
            em.merge(en);
        }
        Endereco ende = new Endereco();
        try {
            ende = em.find(Endereco.class, en.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ende.getId() == null || ende.getEstado() == null) {
            throw new Exception("ERRO ao buscar endereco salvo " + new Date()+"/n id = "+ende.getId()+" estado "+ ende.getEstado());
        }
        return ende;
    }

    public Endereco buscarPorID(Long id) throws Exception {
        if (id < 0) {
            throw new Exception("Erro ao buscar por id      id<0 " + new Date());
        }
        Endereco ende = new Endereco(id);
        try {
            ende = em.find(Endereco.class, ende.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ende.getEstado() == null) {
            throw new Exception("Erro ao buscar por id      Estado = null " + new Date());
        }
        return ende;
    }

    public List<Endereco> buscarTodos() {
        Query q = em.createNamedQuery("Endereco.findAll");
        List<Endereco> EnderecosLista = new LinkedList();
        try {
            System.out.println("Tentando buscar todos Enderecos " + new Date());
            EnderecosLista = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar todos os enderecos " + new Date());
        }
        System.out.println("Enderecos consultados com sucesso " + new Date());
        return EnderecosLista;
    }

    public List<Endereco> BuscarPorRua(String Rua) {
        Query q = em.createNamedQuery("Endereco.ProcurarPorRua");
        q.setParameter("rua", Rua);
        List<Endereco> EnderecosLista = new LinkedList();
        try {
            System.out.println("Tentando buscar Enderecos por rua " + new Date());
            EnderecosLista = q.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar Enderecos por rua " + new Date());
            e.printStackTrace();
        }
        System.out.println("Enderecos consultados com sucesso " + new Date());
        return EnderecosLista;
    }

    public List<Endereco> BuscarPorCidade(String cidade) {
        Query q = em.createNamedQuery("Endereco.ProcurarPorCidade");
        q.setParameter("cidade", cidade);
        List<Endereco> EnderecosLista = new LinkedList();
        try {
            System.out.println("Tentando buscar Enderecos por cidade " + new Date());
            EnderecosLista = q.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar Enderecos por cidade " + new Date());
            e.printStackTrace();
        }
        System.out.println("Enderecos consultados com sucesso " + new Date());
        return EnderecosLista;
    }

    public List<Endereco> BuscarPorEstado(String estado) {
        Query q = em.createNamedQuery("Endereco.ProcurarPorEstado");
        q.setParameter("estado", estado);
        List<Endereco> EnderecosLista = new LinkedList();
        try {
            System.out.println("Tentando buscar Enderecos por estado " + new Date());
            EnderecosLista = q.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar Enderecos por estado " + new Date());
            e.printStackTrace();
        }
        System.out.println("Enderecos consultados com sucesso " + new Date());
        return EnderecosLista;
    }
    public void remover (long id) throws Exception{
        if(id<0){
            throw new Exception("ERRO ao deletar Endereco id < 0 "+new Date());
        }
        try{
        Endereco ende = em.find(Endereco.class, id);
        if(ende == null){
            throw new Exception("ERRO Endereco a ser deletado e dsconhecido");
        }
        em.remove(ende);
        }catch(Exception e){
            throw new Exception("ERRO ao deletar Endereco "+ new Date()+"        "+e.getMessage());
        }
    }
}
