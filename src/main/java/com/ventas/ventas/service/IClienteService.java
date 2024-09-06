
package com.ventas.ventas.service;

import com.ventas.ventas.model.Cliente;
import java.util.List;


public interface IClienteService {
    
    public List <Cliente> listarClientes();
    
    public Cliente obtenerClientePorId(Long id);
    
    public Cliente guardarCliente(Cliente cliente);
    
    public Cliente editarCliente(Cliente cliente);
    
    public void eliminarCliente(Long id);
    
}
