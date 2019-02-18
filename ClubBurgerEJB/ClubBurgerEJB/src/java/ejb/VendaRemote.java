/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Venda;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface VendaRemote {
    public Venda salvar(Venda v) throws Exception;
    public Venda BuscarPorId(long id) throws Exception;
    public List<Venda> buscarTodos() throws Exception;
    public void remover(long id) throws Exception;
}
