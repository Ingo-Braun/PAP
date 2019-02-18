/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.EntregaDAO;
import Modelo.Entrega;
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
public class EntregaBean implements EntregaRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Entrega salvar(Entrega e) throws Exception {
        EntregaDAO dao = new EntregaDAO(em);
        return dao.salvar(e);
    }

    @Override
    public Entrega BuscarPorId(long id) throws Exception {
        EntregaDAO dao = new EntregaDAO(em);
        return dao.BuscarPorID(id);
    }

    @Override
    public List<Entrega> BuscarTodos() throws Exception {
        EntregaDAO dao = new EntregaDAO(em);
        return dao.BuscarTodos();
    }

    @Override
    public List<Entrega> BuscarPorDataentrega(Date data) throws Exception {
        EntregaDAO dao = new EntregaDAO(em);
        return dao.BuscarPorDataentrega(data);
    }

    @Override
    public void remover(long id) throws Exception {
        EntregaDAO dao = new EntregaDAO(em);
        dao.remover(id);
    }

}
