
package com.ventas.ventas.service;

import com.ventas.ventas.model.Producto;
import java.util.List;


public interface IProductoService {
    public List<Producto> listarProductos();
    
    public Producto obtenerProductoPorId(Long id);
    
    public Producto guardarProducto(Producto producto);
    
    public Producto editarProducto(Producto producto);
    
    void eliminarProducto(Long id);
    
    public List<Producto> traerBajoStock();
}
