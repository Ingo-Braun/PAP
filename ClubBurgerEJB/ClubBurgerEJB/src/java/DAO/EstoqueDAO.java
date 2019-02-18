/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Estoque;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ingo
 */
public class EstoqueDAO {

    private EntityManager em;

    public EstoqueDAO(EntityManager em) {
        this.em = em;
    }

    public Estoque salvar(Estoque estoque) throws Exception {
        System.out.println("Tentando salvar novo estoque " + new Date());
        try {
            if (estoque.getId() == null) {
                System.out.println("Salvando novo estoque " + new Date());
                em.persist(estoque);
            } else {
                System.out.println("Atualizando estoque " + new Date());
                em.merge(estoque);
            }
        } catch (Exception e) {
            throw new Exception("ERRO ao salvar Estoque " + new Date() + "                  " + e.getMessage());
        }
        Estoque e = new Estoque();
        try {
            System.out.println("Buscando Estoque salvo  com id = "+e.getId()+" "+ new Date());
            e = em.find(Estoque.class, estoque.getId());
        } catch (Exception ex) {
            throw new Exception("ERRO ao buscar Estoque salvo id = " + estoque.getId() + "  " + new Date() + "                 " + ex.getMessage());
        }
        return e;
    }

    public Estoque BuscarPorId(Long id) throws Exception {
        System.out.println("Buscando Estoque por id " + id + " " + new Date());
        Estoque es = new Estoque(id);
        try {
            em.find(Estoque.class, es.getId());
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar estoque por id " + new Date() + "                      " + e.getMessage());
        }
        return es;
    }
    
    public List<Estoque> buscarTodos() throws Exception{
        System.out.println("Buscando todos os estoques "+ new Date());
        List<Estoque> lista = new LinkedList();
        try{
            Query q = em.createNamedQuery("Estoque.BuscarTodos");
            lista = q.getResultList();
        }catch(Exception e){
            throw new Exception("ERRO ao buscar todos os estoques "+ new Date()+"                "+e.getMessage());
        }
        return lista;
    }
    public void remover(long id)throws Exception{
        if(id<0){
            throw new Exception("ERRO ao remover Estoque id < 0 "+ new Date());
        }
        try{
            Estoque e = em.find(Estoque.class, id);
            if(e == null){
                throw new Exception("ERRO Estoque a ser deletado nao encontrado");
            }
            em.remove(e);
        }catch(Exception e){
            throw new Exception("ERRO ao remover Estoque "+ new Date()+"    "+e.getMessage());
        }
    } 
    
}
