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
public class Rubro extends BaseEntidad {

    private String denominacion;

    // Conexión con Producto
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    //Métodos para los productos
    public void agregarProductos(Producto prod) {
        productos.add(prod);
    }

    public void mostrarProductos() {
        System.out.println("Productos del rubro " + denominacion + ":");
        for (Producto producto : productos) {
            System.out.println("Tipo de producto: " + producto.getTipo() +
                    ", Tiempo estimado de cocción: " + producto.getTiempoEstimadoCocina() +
                    ", Denominación: " + producto.getDenominacion() +
                    ", Precio de venta: " + producto.getPrecioVenta() +
                    ", Precio de compra: " + producto.getPrecioCompra() +
                    ", Stock actual: " + producto.getStockActual() +
                    ", Stock mínimo: " + producto.getStockMinimo() +
                    ", Unidad de medida: " + producto.getUnidadMedida() +
                    ", Foto: " + producto.getFoto() +
                    ", Receta: " + producto.getReceta());
        }

    }

}
