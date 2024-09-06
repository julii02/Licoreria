
package com.ventas.ventas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClienteVentaDTO {
    private Long codigoVenta;
    private Double totalVenta;
    private int cantidadProductos;
    private String nombreCliente;
    private String apellidoCliente;

    public ClienteVentaDTO() {
    }
    
    public ClienteVentaDTO(Long codigoVenta, Double totalVenta, int cantidadProductos, String nombreCliente, String apellidoCliente) {
        this.codigoVenta = codigoVenta;
        this.totalVenta = totalVenta;
        this.cantidadProductos = cantidadProductos;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }
    
    
}
