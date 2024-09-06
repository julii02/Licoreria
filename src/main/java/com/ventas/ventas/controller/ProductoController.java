
package com.ventas.ventas.controller;

import com.ventas.ventas.model.Producto;
import com.ventas.ventas.service.IProductoService;
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
public class ProductoController {
    @Autowired
    private IProductoService productoService;
    
    // Crear un producto
    @PostMapping("producto/crear")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    // Listar todos los productos
    @GetMapping("producto/traer")
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // Obtener un producto por su código
    @GetMapping("producto/traer/{codigo_producto}")
    public Producto obtenerProductoPorId(@PathVariable Long codigo_producto) {
        return productoService.obtenerProductoPorId(codigo_producto);
    }

    // Eliminar un producto por su código
    @DeleteMapping("producto/eliminar/{codigo_producto}")
    public void eliminarProducto(@PathVariable Long codigo_producto) {
        productoService.eliminarProducto(codigo_producto);
    }
    
    @PutMapping("producto/editar/{codigo_producto}")
    public Producto editarProducto(@RequestBody Producto productoActualizado) {
        return productoService.guardarProducto(productoActualizado); // Usamos guardarProducto que internamente hace un save
    }
    
    @GetMapping("producto/traerbajostock")
    public List<Producto> listarProductosBajoStock() {
        return productoService.traerBajoStock();
    }
}
