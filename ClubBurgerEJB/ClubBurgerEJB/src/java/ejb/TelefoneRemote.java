/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Telefone;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface TelefoneRemote {

    public Telefone salvar(Telefone tel) throws Exception;
    public Telefone BuscarPorId(Long id) throws Exception;
    public List<Telefone> buscarTodos() throws Exception;
    public List<Telefone> BuscarPorNumeroDeTelefone(Integer numero) throws Exception;
    public void remover(long id) throws Exception;

}
