/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Modelo.Cliente;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ingo
 */
@Remote
public interface ClienteRemote {

    public Cliente Salvar(Cliente cli) throws Exception;

    public Cliente BuscarPorId(Long id) throws Exception;

    public List<Cliente> getAllCliente() throws Exception;

    public List<Cliente> BuscarPorNome(String nome) throws Exception;

    public List<Cliente> BuscarPorSobrenome(String sobrenome) throws Exception;

    public List<Cliente> BuscarPorEmail(String email) throws Exception;

    public List<Cliente> BuscarPorCpf(String cpf) throws Exception;


}
