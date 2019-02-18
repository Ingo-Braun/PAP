/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Estoque;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface EstoqueRemote {
    public Estoque salvar(Estoque estoque) throws Exception;
    public Estoque BuscarPorId(Long id) throws Exception;
    public List<Estoque> buscarTodos() throws Exception;
    public void remover(long id) throws Exception;
}
