/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.EstoqueDAO;
import Modelo.Estoque;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class EstoqueBean implements EstoqueRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Estoque salvar(Estoque estoque) throws Exception {
        EstoqueDAO dao = new EstoqueDAO(em);
        return dao.salvar(estoque);
    }

    @Override
    public Estoque BuscarPorId(Long id) throws Exception {
        EstoqueDAO dao = new EstoqueDAO(em);
        return dao.BuscarPorId(id);
    }

    @Override
    public List<Estoque> buscarTodos() throws Exception {
        EstoqueDAO dao = new EstoqueDAO(em);
        return dao.buscarTodos();
    }

    @Override
    public void remover(long id) throws Exception {
        EstoqueDAO dao = new EstoqueDAO(em);
        dao.remover(id);
    }

}
