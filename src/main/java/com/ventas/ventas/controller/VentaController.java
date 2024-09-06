
package com.ventas.ventas.controller;

import com.ventas.ventas.dto.ClienteVentaDTO;
import com.ventas.ventas.model.Producto;
import com.ventas.ventas.model.Venta;
import com.ventas.ventas.service.IVentaService;
import java.time.LocalDate;
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
public class VentaController {
    @Autowired
    private IVentaService ventaService;
    
    // Crear una venta
    @PostMapping("venta/crear")
    public Venta crearVenta(@RequestBody Venta venta) {
        return ventaService.guardarVenta(venta);
    }

    // Listar todas las ventas
    @GetMapping("venta/traer")
    public List<Venta> listarVentas() {
        return ventaService.listarVentas();
    }

    // Obtener una venta por su código
    @GetMapping("venta/{codigo_venta}")
    public Venta obtenerVentaPorId(@PathVariable Long codigo_venta) {
        return ventaService.obtenerVentaPorId(codigo_venta);
    }

    // Eliminar una venta por su código
    @DeleteMapping("venta/eliminar/{codigo_venta}")
    public void eliminarVenta(@PathVariable Long codigo_venta) {
        ventaService.eliminarVenta(codigo_venta);
    }

    // Editar una venta por su código
    @PutMapping("venta/editar/{codigo_venta}")
    public Venta editarVenta(@RequestBody Venta ventaActualizada) {
        return ventaService.guardarVenta(ventaActualizada); // Usamos guardarVenta que internamente hace un save
    }
    
    // Obtener una venta por su código
    @GetMapping("venta/productos/{codigo_venta}")
    public List<Producto> productosPorVenta(@PathVariable Long codigo_venta) {
        return ventaService.productosDeVenta(codigo_venta);
    }
    
    @GetMapping("venta/dia/{diaVenta}")
    public String diaVenta(@PathVariable LocalDate diaVenta) {
        return ventaService.ventasPorDia(diaVenta);
    }
    
    @GetMapping("venta/ventamascara")
    public ClienteVentaDTO ventaMasCara() {
        return ventaService.ventaMasCara();
    }
}
