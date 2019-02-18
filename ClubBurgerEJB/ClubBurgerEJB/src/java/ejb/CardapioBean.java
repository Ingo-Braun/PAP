/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.CardapioDAO;
import Modelo.Cardapio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class CardapioBean implements CardapioRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Cardapio salvar(Cardapio cardapio) throws Exception {
        CardapioDAO dao = new CardapioDAO(em);
        return dao.salvar(cardapio);
    }

    @Override
    public Cardapio BuscarPorID(long id) throws Exception {
    CardapioDAO dao = new CardapioDAO(em);
        return dao.BuscarPorID(id);    
    }

    @Override
    public List<Cardapio> BuscarTodos() throws Exception {
    CardapioDAO dao = new CardapioDAO(em);
        return dao.BuscarTodos();
    }

    @Override
    public List<Cardapio> BuscarPorNome(String nome) throws Exception {
    CardapioDAO dao = new CardapioDAO(em);
        return dao.BuscarPorNome(nome);
    }

    @Override
    public List<Cardapio> BuscarPorDescricao(String desc) throws Exception {
    CardapioDAO dao = new CardapioDAO(em);
        return dao.BuscarPorDescricao(desc);
    }

}
