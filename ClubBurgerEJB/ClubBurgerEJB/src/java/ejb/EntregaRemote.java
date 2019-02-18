/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Entrega;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface EntregaRemote {
    public Entrega salvar(Entrega e) throws Exception;
    public Entrega BuscarPorId(long id) throws Exception;
    public List<Entrega> BuscarTodos() throws Exception;
    public List<Entrega> BuscarPorDataentrega(Date data) throws Exception;
    public void remover(long id) throws Exception;
}
