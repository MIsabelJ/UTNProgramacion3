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
public class Cliente extends BaseEntidad {

    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;

    // Conexión con Pedido
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    //Métodos para los pedidos
    public void agregarPedidos(Pedido pedi) {
        pedidos.add(pedi);
    }

    public void mostrarPedidos() {
        System.out.println("Datos del cliente: " + nombre + " " + apellido +
                ", Teléfono: " + telefono + ", " + "Email: " + mail);
        System.out.println("Pedidos del cliente: ");
        for (Pedido pedido : pedidos) {
            System.out.println("Fecha: " + pedido.getFecha() +
                    ", Estado: " + pedido.getEstado() +
                    ", Hora estimada de entrega: " + pedido.getHoraEstimadaEntrega() +
                    ", Tipo de envío: " + pedido.getTipoEnvio() +
                    ", Total: " + pedido.getTotal());
        }
    }

}
