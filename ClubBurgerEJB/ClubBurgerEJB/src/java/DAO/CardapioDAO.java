/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Cardapio;
import Modelo.Cliente;
import Modelo.Pedido;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ingo
 */
public class CardapioDAO {
    private EntityManager em;

    public CardapioDAO(EntityManager em) {
        this.em = em;
    }
   
     public Cardapio salvar(Cardapio cardapio)throws Exception{
        System.out.println("Salvando Pedido "+ new Date());
        try{
            if(cardapio.getId() == null){
                System.out.println("salvando novo Cardapio "+new Date());
                em.persist(cardapio);
            }else{
                System.out.println("Atualizando Cardapio "+ new Date());
                em.merge(cardapio);
            }
        }catch(Exception e){
            throw new Exception("ERRO ao salvar Cardapio "+ new Date()+"    "+ e.getMessage());
        }
        Cardapio card = new Cardapio();
        try{
            card = em.find(Cardapio.class, cardapio.getId());
        }catch(Exception e){
            throw new Exception("ERRO ao buscar Cardapio salvo " + new Date() + "     "+ e.getMessage());
        }
        return card;
    }
   
    public Cardapio BuscarPorID(long id) throws Exception{
        System.out.println("Buscando Cardapio Por id "+ new Date());
        if(id < 0){
            throw new Exception("ERRO ao Buscar Cardapio id < 0 "+ new Date());
        }
        Cardapio cardapio = new Cardapio();
        try{
           cardapio = em.find(Cardapio.class, id);
        }catch(Exception e){
            throw new Exception ("ERRO ao Buscar Cardapio "+ new Date()+"     "+e.getMessage());
        }
        return cardapio;
    }
    
    public List<Cardapio> BuscarTodos()throws Exception{
      Query q=em.createNamedQuery("Cardapio.BuscarTodos");
      List<Cardapio> lista = new ArrayList();
      try{
          lista = q.getResultList();
      }catch(Exception e){
          throw new Exception ("ERRO ao Buscar todos os Cardapios "+ new Date()+"   "+e.getMessage());
      }
      return lista;
    }
    
    public List<Cardapio> BuscarPorNome(String nome)throws Exception{
      Query q=em.createNamedQuery("Cardapio.BuscarTodos");
      q.setParameter("nome", "%"+nome+"%");
      List<Cardapio> lista = new ArrayList();
      try{
          lista = q.getResultList();
      }catch(Exception e){
          throw new Exception ("ERRO ao Buscar todos os Cardapios por nome "+ new Date()+"   "+e.getMessage());
      }
      return lista;
    }
    
    public List<Cardapio> BuscarPorDescricao(String desc)throws Exception{
      Query q=em.createNamedQuery("Cardapio.BuscarTodos");
      q.setParameter("descricao", "%"+desc+"%");
      List<Cardapio> lista = new ArrayList();
      try{
          lista = q.getResultList();
      }catch(Exception e){
          throw new Exception ("ERRO ao Buscar todos os Cardapios por Descricao "+ new Date()+"   "+e.getMessage());
      }
      return lista;
    }
}
