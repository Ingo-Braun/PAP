/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Produto;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ingo
 */
public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public Produto salvar(Produto p) throws Exception {
        try {
            if (p.getId() == null) {
                System.out.println("Salvando novo Produto " + new Date());
                p.setTempo(new Date());
                em.persist(p);
            } else {
                System.out.println("Atualizando Produto " + new Date());
                p.setTempo(new Date());
                em.merge(p);
            }
        } catch (Exception e) {
            throw new Exception("ERRO ao salvar o Produto " + new Date() + "                          " + e.getMessage());
        }
        Produto produto = new Produto();
        try {
            produto = em.find(Produto.class, p.getId());
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar Produto Salvo " + new Date() + "                        " + e.getMessage());
        }
        return produto;
    }
    
    public Produto BuscarPorId(long id) throws Exception{
        System.out.println("Buscando Produto por id "+new Date());
        if(id<0){
            throw new Exception("ERRO ao buscar Produto por id     id < 0 "+new Date());
        }
        Produto p = new Produto(id); 
        try{
            p = em.find(Produto.class, p.getId());
        }catch(Exception e){
            throw new Exception ("ERRO ao buscar Produto por id "+new Date()+"                   "+e.getMessage());
        }
        return p;
    }
    
    public List<Produto> BuscarTodos() throws Exception{
        System.out.println("Buscando todos os produtos "+new Date());
        List<Produto> list = new LinkedList();
        try{
            Query q = em.createNamedQuery("Produto.BuscarTodos");
            list = q.getResultList();
        }catch(Exception e){
            throw new Exception ("ERRO ao buscar todos os Produtos "+new Date()+"                    "+e.getMessage());
        }
        return list;
    }
    
    public List<Produto> BuscarPorNome(String nome) throws Exception{
        System.out.println("Buscando todos os produtos por nome"+new Date());
        List<Produto> list = new LinkedList();
        try{
            Query q = em.createNamedQuery("Produto.BuscarTodos");
            q.setParameter("nome", "%" + nome + "%");
            list = q.getResultList();
        }catch(Exception e){
            throw new Exception ("ERRO ao buscar todos os Produtos por nome "+new Date()+"                    "+e.getMessage());
        }
        return list;
    }
    
    public List<Produto> BuscarPorDescricao(String descricao) throws Exception{
        System.out.println("Buscando todos os produtos por nome"+new Date());
        List<Produto> list = new LinkedList();
        try{
            Query q = em.createNamedQuery("Produto.BuscarPorDescricao");
            q.setParameter("descricao","%" + descricao + "%");
            list = q.getResultList();
        }catch(Exception e){
            throw new Exception ("ERRO ao buscar todos os Produtos por descricao "+new Date()+"                    "+e.getMessage());
        }
        return list;
    }
    public void remover(long id)throws Exception{
        if(id<0){
            throw new Exception("ERRO ao remover Produto id < 0 "+ new Date());
        }
        try{
            Produto p = em.find(Produto.class, id);
            if(p == null){
                throw new Exception("ERRO Produto a ser removido nao encontrado "+ new Date());
            }
            em.remove(p);
        }catch(Exception e){
            throw new Exception("ERRO ao remover produto "+ new Date()+"   "+e.getMessage());
        }
    }
}
