/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Pedido;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface PedidoRemote {
    public Pedido salvar(Pedido p)throws Exception;
    public Pedido BuscarPorID(long id) throws Exception;
    public List<Pedido> BuscarTodos() throws Exception;
    public List<Pedido> BuscarPorStatus(String status) throws Exception;
    public void remover(long id) throws Exception;
}
