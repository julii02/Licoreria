
package com.ventas.ventas.service;

import com.ventas.ventas.dto.ClienteVentaDTO;
import com.ventas.ventas.model.Producto;
import com.ventas.ventas.model.Venta;
import java.time.LocalDate;
import java.util.List;


public interface IVentaService {
    public List<Venta> listarVentas();
    
    public Venta obtenerVentaPorId(Long id);
    
    public Venta guardarVenta(Venta venta);
    
    public Venta editarVenta(Venta venta);
    
    public void eliminarVenta(Long id);
    
    public List<Producto> productosDeVenta(Long id);
    
    public String ventasPorDia(LocalDate fechaVenta);
    
    public int cantidadProductosPorVenta(Long id);
    
    public ClienteVentaDTO ventaMasCara();
}
