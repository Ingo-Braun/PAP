/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Endereco;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface EnderecoRemote {
    public Endereco salvar(Endereco en) throws Exception;
    public Endereco BuscarPorId(Long id) throws Exception;
    public List<Endereco> BuscarTodos() throws Exception;
    public List<Endereco> BuscarPorRua(String rua) throws Exception;
    public List<Endereco> BuscarPorCidade(String cidade) throws Exception;
    public List<Endereco> BuscarPorEstado(String estado) throws Exception;
    public void remover(long id) throws Exception;
}
