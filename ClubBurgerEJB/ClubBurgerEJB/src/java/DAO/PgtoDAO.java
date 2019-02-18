/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Pgto;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ingo
 */
public class PgtoDAO {

    private EntityManager em;

    public PgtoDAO(EntityManager em) {
        this.em = em;
    }

    public Pgto salvar(Pgto p) throws Exception {
        try {
            if (p.getId() == null) {
                System.out.println("Salvando nova forma de pagamento");
                p.setTempo(new Date());
                em.persist(p);
            } else {
                System.out.println("Atualizando pagamento");
                p.setTempo(new Date());
                em.merge(p);
            }
        }catch(Exception e){
            throw new Exception("ERRO ao salvar forma de pagamento "+ new Date()+"                       "+e.getMessage());
        }
        Pgto pgto = new Pgto();
        try{
            pgto = em.find(Pgto.class, p.getId());
        }catch(Exception e){
            throw new Exception ("ERRO ao buscar ultimo pagamento salvo "+new Date()+"                  "+e.getMessage());
        }
        return pgto;
    }
    public Pgto BuscarPorId(long id) throws Exception{
        System.out.println("Buscando Forma de Pagamento "+ new Date());
        if(id < 0){
            throw new Exception("ERRO ao buscar Forma de Pagamento id < 0");
        }
        Pgto p = new Pgto(id);
        try{
            p = em.find(Pgto.class, p.getId());
        }catch(Exception e){
            throw new Exception ("ERRO ao buscar Forma de Pagamento por id "+ new Date()+"                     "+ e.getMessage());
        }
        return p;
    }
    
    public List<Pgto> BuscarTodos() throws Exception{
        System.out.println("Buscanto todas as formas de pagamento "+ new Date());
        List<Pgto> lista = new LinkedList();
        try{
            Query q = em.createNamedQuery("Pgto.BuscarTodos");
            lista = q.getResultList();
        }catch(Exception e){
            throw new Exception("ERRO ao buscat todas as formas de pagamento "+ new Date()+"                     "+e.getMessage());
        }
        return lista;
    }
    
    public List<Pgto> BuscarPorNome(String nome) throws Exception{
        System.out.println("Buscanto todas as formas de pagamento por nome "+ new Date());
        List<Pgto> lista = new LinkedList();
        try{
            Query q = em.createNamedQuery("Pgto.BuscarPorNome");
            q.setParameter("nome", "%"+nome+"%");
            lista = q.getResultList();
        }catch(Exception e){
            throw new Exception("ERRO ao buscat todas as formas de pagamento por nome "+ new Date()+"                     "+e.getMessage());
        }
        return lista;
    }
    
    public List<Pgto> BuscarPorForma(String forma) throws Exception{
        System.out.println("Buscanto todas as formas de pagamento por Forma "+ new Date());
        List<Pgto> lista = new LinkedList();
        try{
            Query q = em.createNamedQuery("Pgto.BuscarPorForma");
            q.setParameter("forma", "%"+forma+"%");
            lista = q.getResultList();
        }catch(Exception e){
            throw new Exception("ERRO ao buscat todas as formas de pagamento por Forma "+ new Date()+"                     "+e.getMessage());
        }
        return lista;
    }
    
    public List<Pgto> BuscarPorDescricao(String descricao) throws Exception{
        System.out.println("Buscanto todas as formas de pagamento por Descricao "+ new Date());
        List<Pgto> lista = new LinkedList();
        try{
            Query q = em.createNamedQuery("Pgto.BuscarPorDescricao");
            q.setParameter("descricao", "%"+descricao+"%");
            lista = q.getResultList();
        }catch(Exception e){
            throw new Exception("ERRO ao buscat todas as formas de pagamento por Descricao "+ new Date()+"                     "+e.getMessage());
        }
        return lista;
    }
    
    public void remover(long id)throws Exception{
        if(id<0){
            throw new Exception("ERRO ao remover Forma de pagamento id < 0 "+ new Date());
        }
        try{
            Pgto p = em.find(Pgto.class, id);
            if(p == null){
                throw new Exception("ERRO Forma de pagamento a ser deletada nao encontrada "+ new Date());
            }
            em.remove(p);
        }catch(Exception e){
            throw new Exception("ERRO ao remover Forma de pagamento "+ new Date()+"   "+e.getMessage());
        }
    }
    
}
