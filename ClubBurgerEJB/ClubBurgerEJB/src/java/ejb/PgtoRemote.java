/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Pgto;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface PgtoRemote {
    public Pgto salvar(Pgto p) throws Exception;
    public Pgto BuscarPorId(long id) throws Exception;
    public List<Pgto> BuscarTodos() throws Exception;
    public List<Pgto> BuscarPorNome(String nome) throws Exception;
    public List<Pgto> BuscarPorForma(String forma) throws Exception;
    public List<Pgto> BuscarPorDescricao(String descricao) throws Exception;
    public void remover(long id) throws Exception;
}
