/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Entrega;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ingo
 */
public class EntregaDAO {

    private EntityManager em;

    public EntregaDAO(EntityManager em) {
        this.em = em;
    }

    public Entrega salvar(Entrega entrega) throws Exception {
        if (entrega.getId() == null) {
            System.out.println("Salvando nova entrega " + new Date());
            entrega.setTempo(new Date());
            try {
                em.persist(entrega);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Erro ao salvar nova Entrega " + new Date());
            }
        } else {
            System.out.println("Atualizando entrega " + new Date());
            try {
                entrega.setTempo(new Date());
                em.merge(entrega);
            } catch (Exception e) {

            }
        }
        Entrega ent = new Entrega();
        try {
            System.out.println("Procurando entrega salva + id Pedido "+entrega.getPedido().getId()+" " + new Date());
            ent = em.find(Entrega.class, entrega.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("erro ao buscar Entrega salva " + new Date());
        }
        return ent;
    }

    public Entrega BuscarPorID(long id) throws Exception {
        System.out.println("Buscando Entrega por id " + new Date());
        if (id < 0) {
            throw new Exception("ERRO ao buscar Entrega por id    id<0 " + new Date());
        }
        Entrega ent = new Entrega(id);
        try {
            ent = em.find(Entrega.class, ent.getId());
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar Entrega por id " + new Date() + "     " + Arrays.toString(e.getStackTrace()));
        }
        return ent;
    }

    public List<Entrega> BuscarTodos() throws Exception {
        System.out.println("Buscando Todas as Entregas " + new Date());
        List<Entrega> entregaLista = new Vector();
        try {
            Query q = em.createNamedQuery("Entrega.BuscarTodos");
            entregaLista = q.getResultList();
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar Todas as Entregas " + new Date() + "   " + e.getMessage());
        }
        return entregaLista;
    }

    public List<Entrega> BuscarPorDataentrega(Date data) throws Exception {
        System.out.println("Buscando Todas as Entregas por data " + new Date());
        List<Entrega> entregaLista = new LinkedList();
        try {
            Query q = em.createNamedQuery("Entrega.BuscarPorDataentrega");
            q.setParameter("dataentrega", data);
            entregaLista = q.getResultList();
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar Entregas por data " + new Date() + "    " + e.getMessage());
        }
        return entregaLista;
    }
   public void remover(long id) throws Exception{
     if(id<0){
         throw new Exception("ERRO ao remover Entrega id < 0 "+ new Date());
     }  
     try{
         Entrega e = em.find(Entrega.class, id);
         if(e == null){
             throw new Exception("ERRO Entrega a ser removida nao existe "+ new Date());
         }
         em.remove(e);
     }catch(Exception e){
         throw new Exception("ERRO ao remover Entrega "+ new Date()+"     "+e.getMessage());
     }
   } 
}
