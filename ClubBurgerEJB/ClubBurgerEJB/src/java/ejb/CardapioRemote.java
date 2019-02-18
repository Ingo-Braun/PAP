/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Cardapio;
import Modelo.Pedido;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface CardapioRemote {
    public Cardapio salvar(Cardapio cardapio)throws Exception;
    public Cardapio BuscarPorID(long id) throws Exception;
    public List<Cardapio> BuscarTodos()throws Exception;
    public List<Cardapio> BuscarPorNome(String nome)throws Exception;
    public List<Cardapio> BuscarPorDescricao(String desc)throws Exception;
}
