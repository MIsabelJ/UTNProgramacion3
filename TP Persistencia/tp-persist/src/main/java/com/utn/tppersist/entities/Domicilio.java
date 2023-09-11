package com.utn.tppersist.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Domicilio extends BaseEntidad {

    private String calle;
    private String numero;
    private String localidad;

    // Conexión con Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Conexión con Pedido
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    // Métodos para los pedidos
    public void agregarPedidos(Pedido pedi) {
        pedidos.add(pedi);
    }

    public void mostrarPedidos() {
        System.out.println("Pedidos de " + calle + " " + numero + ", " + localidad + ": ");
        for (Pedido pedido : pedidos) {
            System.out.println("Fecha: " + pedido.getFecha() +
                    ", Estado: " + pedido.getEstado() +
                    ", Hora estimada de entrega: " + pedido.getHoraEstimadaEntrega() +
                    ", Tipo de envío: " + pedido.getTipoEnvio() +
                    ", Total: " + pedido.getTotal());
        }
    }
}
