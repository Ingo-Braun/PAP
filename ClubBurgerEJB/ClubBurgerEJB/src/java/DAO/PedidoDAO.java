/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Pedido;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ingo
 */
public class PedidoDAO {
    private EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }
    
    public Pedido salvar(Pedido p)throws Exception{
        System.out.println("Salvando Pedido "+ new Date());
        try{
            if(p.getId() == null){
                System.out.println("salvando novo pedido "+new Date());
                p.setTempo(new Date());
                em.persist(p);
            }else{
                System.out.println("Atualizando Pedido "+ new Date());
                em.merge(p);
            }
        }catch(Exception e){
            throw new Exception("ERRO ao salvar pedido "+ new Date()+"    "+ e.getMessage());
        }
        Pedido ped = new Pedido();
        try{
            ped = em.find(Pedido.class, p.getId());
        }catch(Exception e){
            throw new Exception("ERRO ao buscar Pedido salvo " + new Date() + "     "+ e.getMessage());
        }
        return ped;
    }
    
    public Pedido BuscarPorID(long id) throws Exception{
        System.out.println("Buscando Pedido Por id "+ new Date());
        if(id < 0){
            throw new Exception("ERRO ao Buscar Pedido id < 0 "+ new Date());
        }
        Pedido ped = new Pedido();
        try{
           ped = em.find(Pedido.class, id);
        }catch(Exception e){
            throw new Exception ("ERRO ao Buscar pedido "+ new Date()+"     "+e.getMessage());
        }
        return ped;
    }
    
    public List<Pedido> BuscarTodos() throws Exception{
        System.out.println("Buscando Todos os pedidos");
        List<Pedido> lista = new LinkedList();
        try{
            Query q = em.createNamedQuery("Pedido.BuscarTodos");
            lista = q.getResultList();
        }catch(Exception e){
            throw new Exception("ERRO ao buscar todos os pedidos "+ new Date()+"    "+e.getMessage());
        }
        return lista;
    }
    
    public List<Pedido> BuscarPorStatus(String status) throws Exception{
        System.out.println("Buscando Pedidos por status "+ new Date());
        if(status == null || status == ""||status ==" "){
            throw new Exception ("ERRO ao buscar por status   status = null "+ new Date());
        }
        List<Pedido> list = new LinkedList();
        try{
            Query q = em.createNamedQuery("Pedido.BuscarPorStatus");
            q.setParameter("status", status);
            list=q.getResultList();
        }catch(Exception e){
            throw new Exception ("ERRO ao buscar Pedidos por status "+new Date()+"    "+ e.getMessage());
        }
        return list;
    }
    public void remover(long id)throws Exception{
        if(id<0){
            throw new Exception("ERRO ao remover pedido id < 0 "+ new Date());
        }
        try{
            Pedido p = em.find(Pedido.class, id);
            if(p == null){
                throw new Exception("ERRO Pedido a ser deletado nao foi encontrado "+ new Date());
            }
            em.remove(p);
        }catch(Exception e){
            throw new Exception("ERRO ao remover Pedido "+ new Date()+"   "+e.getMessage());
        }
    }
}
