/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Fentrega;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface FentregaRemote {
    public Fentrega salvar(Fentrega f) throws Exception;
    public Fentrega BuscarPorId(Long id) throws Exception;
    public List<Fentrega> BuscarTodos() throws Exception;
    public List<Fentrega> BuscarPorNome(String nome) throws Exception;
    public List<Fentrega> BuscarPorForma(String forma) throws Exception;
    public List<Fentrega> BuscarPorDescricao(String descicao) throws Exception;
    public void remover(long id) throws Exception;
}
