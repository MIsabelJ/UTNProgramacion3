package com.utn.tppersist.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends BaseEntidad {

    private String fecha;

    @Enumerated(EnumType.STRING)
    private Estado estado;
    public enum Estado {
        iniciado,
        preparacion,
        entregado
    }

    private LocalTime horaEstimadaEntrega;

    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;
    public enum TipoEnvio {
        delivery,
        retira
    }

    private Double total;

    // Conexión con Factura
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    // Conexión con DetallePedido
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    // Métodos para los detalles de pedidos
    public void agregarDetalles(DetallePedido detalle) {
        detallePedidos.add(detalle);
    }

    public void mostrarDetalles() {
        System.out.println("Detalles del pedido: ");
        System.out.println("Fecha: " + fecha + ", Estado: " + estado +
                ", Hora estimada de entrega: " + horaEstimadaEntrega +
                ", Tipo de envío: " + tipoEnvio + ", Total: " + total);
        for (DetallePedido detallePedido : detallePedidos) {
            System.out.println("Cantidad de productos: " + detallePedido.getCantidad() +
                    ", Subtotal: " + detallePedido.getSubtotal());
        }
    }
}
