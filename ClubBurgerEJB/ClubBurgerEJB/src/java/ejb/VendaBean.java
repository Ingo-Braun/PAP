/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.VendaDAO;
import Modelo.Venda;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class VendaBean implements VendaRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Venda salvar(Venda v) throws Exception {
        VendaDAO dao = new VendaDAO(em);
        return dao.salvar(v);
    }

    @Override
    public Venda BuscarPorId(long id) throws Exception {
        VendaDAO dao = new VendaDAO(em);
        return dao.BuscarPorId(id);
    }

    @Override
    public List<Venda> buscarTodos() throws Exception {
        VendaDAO dao = new VendaDAO(em);
        return dao.buscarTodos();
    }

    @Override
    public void remover(long id) throws Exception {
        VendaDAO dao = new VendaDAO(em);
        dao.remover(id);
    }

}
