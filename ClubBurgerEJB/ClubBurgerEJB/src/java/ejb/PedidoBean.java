/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.PedidoDAO;
import Modelo.Pedido;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class PedidoBean implements PedidoRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Pedido salvar(Pedido p) throws Exception {
        PedidoDAO dao = new PedidoDAO(em);
        return dao.salvar(p);
    }

    @Override
    public Pedido BuscarPorID(long id) throws Exception {
        PedidoDAO dao = new PedidoDAO(em);
        return dao.BuscarPorID(id);
    }

    @Override
    public List<Pedido> BuscarTodos() throws Exception {
        PedidoDAO dao = new PedidoDAO(em);
        return dao.BuscarTodos();
    }

    @Override
    public List<Pedido> BuscarPorStatus(String status) throws Exception {
        PedidoDAO dao = new PedidoDAO(em);
        return dao.BuscarPorStatus(status);
    }

    @Override
    public void remover(long id) throws Exception {
        PedidoDAO dao = new PedidoDAO(em);
        dao.remover(id);
    }

}
