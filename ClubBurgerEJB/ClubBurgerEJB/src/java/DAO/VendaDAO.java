/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Modelo.Produto;
import Modelo.Venda;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ingo
 */
public class VendaDAO {
    private EntityManager em;

    public VendaDAO(EntityManager em) {
        this.em = em;
    }

    public Venda salvar(Venda v) throws Exception {
        try {
            if (v.getId() == null) {
                System.out.println("Salvando nova Venda " + new Date());
                v.setTempo(new Date());
                em.persist(v);
            } else {
                System.out.println("Atualizando Venda " + new Date());
                v.setTempo(new Date());
                em.merge(v);
            }
        } catch (Exception e) {
            throw new Exception("ERRO ao salvar a Venda " + new Date() + "                          " + e.getMessage());
        }
        Venda produto = new Venda();
        try {
            produto = em.find(Venda.class, v.getId());
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar a Venda Salva " + new Date() + "                        " + e.getMessage());
        }
        return produto;
    }
    
    public Venda BuscarPorId(long id) throws Exception{
        System.out.println("Buscando Venda por id "+new Date());
        if(id<0){
            throw new Exception("ERRO ao buscar a Venda por id     id < 0 "+new Date());
        }
        Venda p = new Venda(id); 
        try{
            p = em.find(Venda.class, p.getId());
        }catch(Exception e){
            throw new Exception ("ERRO ao buscar a Venda por id "+new Date()+"                   "+e.getMessage());
        }
        return p;
    }
    
    public List<Venda> buscarTodos() throws Exception{
      System.out.println("Buscando todas as Vendas "+new Date());
        List<Venda> list = new LinkedList();
        try{
            Query q = em.createNamedQuery("Produto.BuscarTodos");
            list = q.getResultList();
        }catch(Exception e){
            throw new Exception ("ERRO ao buscar todas as Vendas "+new Date()+"                    "+e.getMessage());
        }
        return list;  
    }
    public void remover(long id)throws Exception{
        if(id<0){
            throw new Exception("ERRO ao remover venda id < 0 "+ new Date());
        }
        try{
            Venda v = em.find(Venda.class, id);
            if(v == null){
                throw new Exception("ERRO venda a ser removida nao foi encontrada "+ new Date());
            }
            em.remove(v);
        }catch(Exception e){
            throw new Exception("ERRO ao remover venda "+ new Date() + "   "+ e.getMessage());
        }
    }
}
