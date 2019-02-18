/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.*;
import Modelo.Cliente;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class ClienteBean implements ClienteRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Cliente Salvar(Cliente cli) {

        System.out.println("Novo pedido para salvar cliente " + new Date());
        ClienteDAO cliDao = new ClienteDAO(em);
        Cliente cliente = new Cliente();
        try {
            cliente = cliDao.salvar(cli);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public Cliente BuscarPorId(Long id) throws Exception {
        ClienteDAO dao = new ClienteDAO(em);
        return dao.getClientePorID(id);
    }

    @Override
    public List<Cliente> getAllCliente() throws Exception {
        ClienteDAO dao = new ClienteDAO(em);
        return dao.getAllCliente();
    }

    @Override
    public List<Cliente> BuscarPorNome(String nome) throws Exception {
        ClienteDAO dao = new ClienteDAO(em);
        return dao.BuscarPorNome(nome);
    }

    @Override
    public List<Cliente> BuscarPorSobrenome(String sobrenome) throws Exception {
        ClienteDAO dao = new ClienteDAO(em);
        return dao.BuscarPorSobrenome(sobrenome);
    }

    @Override
    public List<Cliente> BuscarPorEmail(String email) throws Exception {
        ClienteDAO dao = new ClienteDAO(em);
        return dao.BuscarPorEmail(email);
    }

    @Override
    public List<Cliente> BuscarPorCpf(String cpf) throws Exception {
        ClienteDAO dao = new ClienteDAO(em);
        return dao.BuscarPorCpf(cpf);
    }

    @Override
    public void remover(long id) throws Exception {
        ClienteDAO dao = new ClienteDAO(em);
        dao.remover(id);
    }

}
