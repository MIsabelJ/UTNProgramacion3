package com.utn.tppersist.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedido extends BaseEntidad {

    private int cantidad;
    private Double subtotal;

    // Conexión con Producto
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

}
