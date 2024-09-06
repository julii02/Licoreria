
package com.ventas.ventas.service;

import com.ventas.ventas.dto.ClienteVentaDTO;
import com.ventas.ventas.model.Cliente;
import com.ventas.ventas.model.Producto;
import com.ventas.ventas.model.Venta;
import com.ventas.ventas.repository.IProductoRepository;
import com.ventas.ventas.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {
    @Autowired
    private IVentaRepository ventaRepository;
    
    @Autowired
    private IProductoRepository productoRepository;
    
    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta obtenerVentaPorId(Long id) {
        Optional<Venta> venta = ventaRepository.findById(id);
        return venta.orElse(null);
    }

    @Override
    public Venta guardarVenta(Venta venta) {
        // Calcular el total de la venta
    double total = 0.0;
    
    for (Producto producto : venta.getListaProductos()) {
        // Cargar el producto completo desde la base de datos usando su ID
        Producto productoCompleto = productoRepository.findById(producto.getCodigoProducto())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con código: " + producto.getCodigoProducto()));

        // Asegurarse que la cantidad disponible no es null y restar 1
        if (productoCompleto.getCantidadDisponible() > 0) {
            total += productoCompleto.getCosto(); // Sumar el costo de cada producto
            productoCompleto.setCantidadDisponible(productoCompleto.getCantidadDisponible() - 1);
            productoRepository.save(productoCompleto); // Guardar los cambios en el producto
        } else {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + productoCompleto.getNombre());
        }
    }
    venta.setTotal(total); // Establecer el total calculado en la venta
    return ventaRepository.save(venta); // Guardar la venta en la base de datos
    }

    @Override
    public Venta editarVenta(Venta venta) {
        return this.guardarVenta(venta);
    }

    @Override
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public List<Producto> productosDeVenta(Long ventaId) {
        List <Producto> listaProductos = new ArrayList();
        
        Venta venta = this.obtenerVentaPorId(ventaId);
        
        listaProductos = venta.getListaProductos();
        
        return listaProductos;
    }

    @Override
    public String ventasPorDia(LocalDate fechaVenta) {
        List <Venta> listaVentas = this.listarVentas();
        double total = 0;
        int cantidad = 0;
        
        for(Venta venta : listaVentas){
            if(venta.getFechaVenta().isEqual(fechaVenta)){
                total = total + venta.getTotal();
                cantidad = cantidad + 1;
            }
        }
        return "En el dia indicado se hicieron: " + cantidad + " ventas. Y esto suma un total de: " + total;
    }

    @Override
    public ClienteVentaDTO ventaMasCara() {
        List <Venta> listaVentas = this.listarVentas();
        double ventaMayor = 0;
        
        ClienteVentaDTO clienteDTO = new ClienteVentaDTO();
        
        for(Venta venta : listaVentas){
            if(ventaMayor < venta.getTotal()){
                ventaMayor = venta.getTotal();
                clienteDTO.setCodigoVenta(venta.getCodigoVenta());
                clienteDTO.setCantidadProductos(this.cantidadProductosPorVenta(venta.getCodigoVenta()));
                clienteDTO.setTotalVenta(venta.getTotal());
                clienteDTO.setNombreCliente(venta.getUnCliente().getNombre());
                clienteDTO.setApellidoCliente(venta.getUnCliente().getApellido());
                
            }
        }
        
        return clienteDTO;
    }

    @Override
    public int cantidadProductosPorVenta(Long id) {
        Venta venta = this.obtenerVentaPorId(id);
        int cantidadProductos = 0;
        
        for(Producto producto : venta.getListaProductos()){
            cantidadProductos = cantidadProductos + 1;
        }
        return cantidadProductos;
    }
    
}
