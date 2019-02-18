/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.TelefoneDAO;
import Modelo.Telefone;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class TelefoneBean implements TelefoneRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Telefone salvar(Telefone tel) throws Exception {
        TelefoneDAO dao = new TelefoneDAO(em);
        return dao.salvar(tel);
    }

    @Override
    public Telefone BuscarPorId(Long id) throws Exception {
        TelefoneDAO dao = new TelefoneDAO(em);
        return dao.buscarPorID(id);
    }

    @Override
    public List<Telefone> buscarTodos() throws Exception {
        TelefoneDAO dao = new TelefoneDAO(em);
        return dao.buscarTodos();
    }

    @Override
    public List<Telefone> BuscarPorNumeroDeTelefone(Integer numero) throws Exception {
        TelefoneDAO dao = new TelefoneDAO(em);
        return dao.BuscarPorNumeroDeTelefone(numero);
    }

    @Override
    public void remover(long id) throws Exception {
        TelefoneDAO dao = new TelefoneDAO(em);
        dao.remover(id);
    }

}
