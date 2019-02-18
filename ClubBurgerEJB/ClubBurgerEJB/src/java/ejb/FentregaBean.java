/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.FentregaDAO;
import Modelo.Fentrega;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class FentregaBean implements FentregaRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Fentrega salvar(Fentrega f) throws Exception {
        FentregaDAO dao = new FentregaDAO(em);
        return dao.salvar(f);
    }

    @Override
    public Fentrega BuscarPorId(Long id) throws Exception {
        FentregaDAO dao = new FentregaDAO(em);
        return dao.BuscarPorId(id);
    }

    @Override
    public List<Fentrega> BuscarTodos() throws Exception {
        FentregaDAO dao = new FentregaDAO(em);
        return dao.BuscarTodos();
    }

    @Override
    public List<Fentrega> BuscarPorNome(String nome) throws Exception {
        FentregaDAO dao = new FentregaDAO(em);
        return dao.BuscarPorNome(nome);
    }

    @Override
    public List<Fentrega> BuscarPorForma(String forma) throws Exception {
        FentregaDAO dao = new FentregaDAO(em);
        return dao.BuscarPorForma(forma);
    }

    @Override
    public List<Fentrega> BuscarPorDescricao(String descicao) throws Exception {
        FentregaDAO dao = new FentregaDAO(em);
        return dao.BuscarPorDescricao(descicao);
    }

    @Override
    public void remover(long id) throws Exception {
        FentregaDAO dao = new FentregaDAO(em);
        dao.remover(id);
    }

}
