
package com.ventas.ventas.controller;

import com.ventas.ventas.model.Cliente;
import com.ventas.ventas.service.IClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    @Autowired
    private IClienteService clienteService;
    
    // Crear un cliente
    @PostMapping("cliente/crear")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    // Listar todos los clientes
    @GetMapping("cliente/traer")
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    // Obtener un cliente por su ID
    @GetMapping("cliente/traer/{id_cliente}")
    public Cliente obtenerClientePorId(@PathVariable Long id_cliente) {
        return clienteService.obtenerClientePorId(id_cliente);
    }

    // Eliminar un cliente por su ID
    @DeleteMapping("cliente/eliminar/{id_cliente}")
    public void eliminarCliente(@PathVariable Long id_cliente) {
        clienteService.eliminarCliente(id_cliente);
    }

    // Editar un cliente por su ID
    @PutMapping("cliente/editar/{id_cliente}")
    public Cliente editarCliente(@RequestBody Cliente clienteActualizado) {
        return clienteService.guardarCliente(clienteActualizado); // Usamos guardarCliente que internamente hace un save
    }
}
