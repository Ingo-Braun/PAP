/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Produto;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface ProdutoRemote {
    public Produto salvar(Produto p) throws Exception;
    public Produto BuscarPorId(long id) throws Exception;
    public List<Produto> BuscarTodos() throws Exception;
    public List<Produto> BuscarPorNome(String nome) throws Exception;
    public List<Produto> BuscarPorDescricao(String descricao) throws Exception;
    public void remover(long id) throws Exception;
}
