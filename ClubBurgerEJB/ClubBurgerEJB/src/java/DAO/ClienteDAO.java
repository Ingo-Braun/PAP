/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Cliente;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ingo
 */
public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public Cliente salvar(Cliente cliente)
            throws Exception {
        try {
            if (cliente.getId() == null) {
                try {
                    System.out.println("Salvando novo Cliente " + new Date());
                    cliente.setTempo(new Date());
                    this.em.persist(cliente);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                cliente.setTempo(new Date());
                em.merge(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("deu ruim");
        }

        cliente = em.find(Cliente.class, cliente.getId());
        return cliente;
    }

    public Cliente getClientePorID(Long id) throws Exception {
        Cliente cli = new Cliente(id);
        try {
            em.find(Cliente.class, cli.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cli;
    }

    public List<Cliente> getAllCliente() throws Exception {
        Query q = em.createNamedQuery("Cliente.findAll");
        return q.getResultList();
    }

    public List<Cliente> BuscarPorNome(String nome) throws Exception {
        Query q = em.createNamedQuery("Cliente.BuscarPorNome");
        q.setParameter("nome", nome);
        return q.getResultList();
    }

    public List<Cliente> BuscarPorSobrenome(String sobrenome) throws Exception {
        Query q = em.createNamedQuery("Cliente.BuscarPorSobrenome");
        q.setParameter("sobrenome", sobrenome);
        return q.getResultList();
    }

    public List<Cliente> BuscarPorEmail(String email) throws Exception {
        Query q = em.createNamedQuery("Cliente.BuscarPorEmail");
        q.setParameter("email", email);
        return q.getResultList();
    }

    public List<Cliente> BuscarPorCpf(String cpf) throws Exception {
        Query q = em.createNamedQuery("Cliente.BuscarPorCpf");
        q.setParameter("cpf", cpf);
        return q.getResultList();
    }

    public void remover(long id) throws Exception {
        Cliente cli = new Cliente(id);
        try {
            cli = em.find(Cliente.class, cli.getId());
            if(cli != null){
                em.remove(cli);
            }else{
             throw new Exception("Cliente a ser deletado desconhecido "+new Date());   
            }
        } catch (Exception e) {
            throw new Exception("ERRO ao deletar Cliente "+ new Date()+"        "+e.getMessage());
        }
    }
}
