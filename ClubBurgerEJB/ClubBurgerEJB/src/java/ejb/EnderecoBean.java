/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.EnderecoDAO;
import Modelo.Endereco;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingo
 */
@Stateless
public class EnderecoBean implements EnderecoRemote {

    @PersistenceContext(unitName = "ClubBurgerEJBPU")
    private EntityManager em;

    @Override
    public Endereco salvar(Endereco en) throws Exception {
        EnderecoDAO dao = new EnderecoDAO(em);
        en = dao.salvar(en);
        return en;
    }

    @Override
    public Endereco BuscarPorId(Long id) throws Exception {
        Endereco en = new Endereco();
        EnderecoDAO dao = new EnderecoDAO(em);
        en = dao.buscarPorID(id);
        return en;
    }

    @Override
    public List<Endereco> BuscarTodos() throws Exception {
        EnderecoDAO dao = new EnderecoDAO(em);
        List<Endereco> EnderecoList = new LinkedList();
        EnderecoList = dao.buscarTodos();
        return EnderecoList;
    }

    @Override
    public List<Endereco> BuscarPorRua(String rua) throws Exception {
        EnderecoDAO dao = new EnderecoDAO(em);
        List<Endereco> EnderecoList = new LinkedList();
        EnderecoList = dao.BuscarPorRua(rua);
        return EnderecoList;
    }

    @Override
    public List<Endereco> BuscarPorCidade(String cidade) throws Exception {
        EnderecoDAO dao = new EnderecoDAO(em);
        List<Endereco> EnderecoList = new LinkedList();
        EnderecoList = dao.BuscarPorCidade(cidade);
        return EnderecoList;
    }

    @Override
    public List<Endereco> BuscarPorEstado(String estado) throws Exception {
        EnderecoDAO dao = new EnderecoDAO(em);
        List<Endereco> EnderecoList = new LinkedList();
        EnderecoList = dao.BuscarPorEstado(estado);
        return EnderecoList;
    }

    @Override
    public void remover(long id) throws Exception {
        EnderecoDAO dao = new EnderecoDAO(em);
        dao.remover(id);
    }

}
