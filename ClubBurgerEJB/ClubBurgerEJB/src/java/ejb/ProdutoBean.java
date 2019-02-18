/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.ProdutoDAO;
import Modelo.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class ProdutoBean implements ProdutoRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Produto salvar(Produto p) throws Exception {
        ProdutoDAO dao = new ProdutoDAO(em);
        return dao.salvar(p);
    }

    @Override
    public Produto BuscarPorId(long id) throws Exception {
        ProdutoDAO dao = new ProdutoDAO(em);
        return dao.BuscarPorId(id);
    }

    @Override
    public List<Produto> BuscarTodos() throws Exception {
        ProdutoDAO dao = new ProdutoDAO(em);
        return dao.BuscarTodos();
    }

    @Override
    public List<Produto> BuscarPorNome(String nome) throws Exception {
        ProdutoDAO dao = new ProdutoDAO(em);
        return dao.BuscarPorNome(nome);
    }

    @Override
    public List<Produto> BuscarPorDescricao(String descricao) throws Exception {
        ProdutoDAO dao = new ProdutoDAO(em);
        return dao.BuscarPorDescricao(descricao);
    }

    @Override
    public void remover(long id) throws Exception {
        ProdutoDAO dao = new ProdutoDAO(em);
        dao.remover(id);
    }

}
