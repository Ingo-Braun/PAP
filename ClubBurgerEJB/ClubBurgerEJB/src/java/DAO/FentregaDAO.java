/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Fentrega;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ingo
 */
public class FentregaDAO {

    private EntityManager em;

    public FentregaDAO(EntityManager em) {
        this.em = em;
    }

    public Fentrega salvar(Fentrega f) throws Exception {
        System.out.println("Salvando Forma de entrega " + new Date());
        try {
            if (f.getId() == null) {
                System.out.println("salvando nova entrega " + new Date());
                f.setTempo(new Date());
                em.persist(f);
            } else {
                System.out.println("atualizando Forma de entrega " + new Date());
                em.merge(f);
            }
        } catch (Exception e) {
            throw new Exception("ERRO ao salvar forma de entrega " + new Date() + "                    " + e.getMessage());
        }
        Fentrega fe = new Fentrega();
        System.out.println("Buscando forma de entrega salva " + new Date());
        try {
            fe = em.find(Fentrega.class, f.getId());
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar entrega salva " + new Date() + "                      " + e.getMessage());
        }
        return fe;
    }

    public Fentrega BuscarPorId(Long id) throws Exception {
        System.out.println("Buscando Forma de entrega por id " + new Date());
        if (id == null || id < 0) {
            throw new Exception("ERRO ao buscar forma de entrega por id    id < 0 ou nullo " + new Date());
        }
        Fentrega f = new Fentrega(id);
        try {
            f = em.find(Fentrega.class, f.getId());
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar Forma de entrega por id " + new Date() + "                    " + e.getMessage());
        }
        return f;
    }

    public List<Fentrega> BuscarTodos() throws Exception {
        System.out.println("Buscando todas as formas de entrega " + new Date());
        List<Fentrega> list = new LinkedList();
        try {
            Query q = em.createNamedQuery("Fentrega.BuscarTodos");
            list = q.getResultList();
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar todas as formas de entrega " + new Date() + "                     " + e.getMessage());
        }
        return list;
    }

    public List<Fentrega> BuscarPorNome(String nome) throws Exception {
        System.out.println("Buscando todas as formas de entrega por nome" + new Date());
        List<Fentrega> list = new LinkedList();
        try {
            Query q = em.createNamedQuery("Fentrega.BuscarPorNome");
            q.setParameter("nome", "%" + nome + "%");
            list = q.getResultList();
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar todas as formas de entrega por nome " + new Date() + "                     " + e.getMessage());
        }
        return list;
    }

    public List<Fentrega> BuscarPorForma(String forma) throws Exception {
        System.out.println("Buscando todas as formas de entrega por forma" + new Date());
        List<Fentrega> list = new LinkedList();
        try {
            Query q = em.createNamedQuery("Fentrega.BuscarPorForma");
            q.setParameter("forma", "%" + forma + "%");
            list = q.getResultList();
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar todas as formas de entrega por forma " + new Date() + "                     " + e.getMessage());
        }
        return list;
    }

    public List<Fentrega> BuscarPorDescricao(String descicao) throws Exception {
        System.out.println("Buscando todas as formas de entrega por descricao" + new Date());
        List<Fentrega> list = new LinkedList();
        try {
            Query q = em.createNamedQuery("Fentrega.BuscarPorDescricao");
            q.setParameter("descricao", "%" + descicao + "%");
            list = q.getResultList();
        } catch (Exception e) {
            throw new Exception("ERRO ao buscar todas as formas de entrega por descricao " + new Date() + "                     " + e.getMessage());
        }
        return list;
    }

    public void remover(long id) throws Exception {
        if (id < 0) {
            throw new Exception("ERRO ao deletar Forma de entrega id < 0");
        }
        try {
            Fentrega f = em.find(Fentrega.class, id);
            if (f == null) {
                throw new Exception("ERRO Forma de entrega a ser removida nao encontrada " + new Date());
            }
            em.remove(f);
        } catch (Exception e) {
            throw new Exception("ERRO ao remover Forma de entrega " + new Date() + "    " + e.getMessage());
        }
    }

}
