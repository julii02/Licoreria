
package com.ventas.ventas.service;

import com.ventas.ventas.model.Producto;
import com.ventas.ventas.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {
    
    @Autowired
    private IProductoRepository productoRepository;
    
    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
       Optional<Producto> producto = productoRepository.findById(id);
       return producto.orElse(null);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
       return productoRepository.save(producto);
    }

    @Override
    public Producto editarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);

    }

    @Override
    public List<Producto> traerBajoStock() {
        List <Producto> listaProductos = new ArrayList();
        
        for(Producto produ : this.listarProductos()){
            if(produ.getCantidadDisponible() < 5){
                listaProductos.add(produ);
            }
        }
        return listaProductos;
    }
    
}
