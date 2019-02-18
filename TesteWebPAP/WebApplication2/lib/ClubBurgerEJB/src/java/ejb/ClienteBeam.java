/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.ClienteDAO;
import Modelo.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class ClienteBeam implements ClienteRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Cliente Salvar(Cliente cli) throws Exception {
        ClienteDAO dao = new ClienteDAO(em);
        return dao.salvar(cli);
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

}
