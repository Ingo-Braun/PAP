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

    private EntityManager entityManager;

    public ClienteDAO(EntityManager entityManager) throws Exception {
        this.entityManager = entityManager;
    }

    public Cliente salvar(Cliente cliente)
            throws Exception {
        if (cliente.getNome() == null) {
            System.out.println("Nome = null " + new Date());
            return null;
        }
        if (cliente.getSobrenome() == null) {
            System.out.println("Sobrenome = null " + new Date());
            return null;
        }
        if (cliente.getEmail() == null) {
            System.out.println("Email = null " + new Date());
            return null;
        }
        if (cliente.getSenha() == null) {
            System.out.println("Senha = null " + new Date());
            return null;
        }
        if (cliente.getEnderecoList() == null) {
            System.out.println("Sem endereço " + new Date());
            return null;
        }
        if (cliente.getTelefoneList() == null) {
            System.out.println("Sem telefone " + new Date());
            return null;
        }
        if (cliente.getDatacria() == null) {
            cliente.setDatacria(new Date());
            return null;
        }
        if (cliente.getTempo() == null) {
            cliente.setTempo(new Date());
            return null;
        }
        try {
            if (cliente.getId() == null) {
                cliente.setTempo(new Date());
                this.entityManager.persist(cliente);
            } else {
                cliente.setTempo(new Date());
                entityManager.merge(cliente);
            }
        } catch (Exception e) {

            throw new Exception("deu ruim");
        }

        cliente = entityManager.find(Cliente.class, cliente);
        return cliente;
    }
    public Cliente getClientePorID(Long id)throws Exception{
        Cliente cli = new Cliente(id);
        try{
           entityManager.find(Cliente.class, cli.getId());
        }catch (Exception e){
         e.printStackTrace();
        }    
        return cli;
    }
    
    public List<Cliente> getAllCliente()throws Exception{
        Query q = entityManager.createNamedQuery("Cliente.findAll");
        return q.getResultList();
    }
    public List<Cliente> BuscarPorNome(String nome)throws Exception{
        Query q = entityManager.createNamedQuery("Cliente.BuscarPorNome");
        q.setParameter("nome", nome);
        return q.getResultList();
    }
    public List<Cliente> BuscarPorSobrenome(String sobrenome)throws Exception{
        Query q = entityManager.createNamedQuery("Cliente.BuscarPorSobrenome");
        q.setParameter("sobrenome", sobrenome);
        return q.getResultList();
    }
    public List<Cliente> BuscarPorEmail(String email)throws Exception{
        Query q = entityManager.createNamedQuery("Cliente.BuscarPorEmail");
        q.setParameter("email", email);
        return q.getResultList();
    }
    public List<Cliente> BuscarPorCpf(String cpf)throws Exception{
        Query q = entityManager.createNamedQuery("Cliente.BuscarPorCpf");
        q.setParameter("cpf", cpf);
        return q.getResultList();
    }
}

