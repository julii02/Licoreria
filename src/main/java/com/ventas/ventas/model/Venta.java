
package com.ventas.ventas.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigoVenta;

    private LocalDate fechaVenta;
    private Double total;
    @ManyToMany
    @JoinTable(
        name = "venta_producto",
        joinColumns = @JoinColumn(name = "codigoVenta"),
        inverseJoinColumns = @JoinColumn(name = "codigoProducto")
    )
    private List<Producto> listaProductos;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente")
    private Cliente unCliente;

    public Venta() {
    }

    public Venta(Long codigoVenta, LocalDate fechaVenta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }
    
    
}
