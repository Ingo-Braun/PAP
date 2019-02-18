/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.PgtoDAO;
import Modelo.Pgto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class PgtoBean implements PgtoRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Pgto salvar(Pgto p) throws Exception {
        PgtoDAO dao = new PgtoDAO(em);
        return dao.salvar(p);
    }

    @Override
    public Pgto BuscarPorId(long id) throws Exception {
        PgtoDAO dao = new PgtoDAO(em);
        return dao.BuscarPorId(id);
    }

    @Override
    public List<Pgto> BuscarTodos() throws Exception {
        PgtoDAO dao = new PgtoDAO(em);
        return dao.BuscarTodos();
    }

    @Override
    public List<Pgto> BuscarPorNome(String nome) throws Exception {
        PgtoDAO dao = new PgtoDAO(em);
        return dao.BuscarPorNome(nome);
    }

    @Override
    public List<Pgto> BuscarPorForma(String forma) throws Exception {
        PgtoDAO dao = new PgtoDAO(em);
        return dao.BuscarPorForma(forma);
    }

    @Override
    public List<Pgto> BuscarPorDescricao(String descricao) throws Exception {
        PgtoDAO dao = new PgtoDAO(em);
        return dao.BuscarPorDescricao(descricao);
    }

    @Override
    public void remover(long id) throws Exception {
        PgtoDAO dao = new PgtoDAO(em);
        dao.remover(id);
    }

}
