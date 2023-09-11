package com.utn.tppersist.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura extends BaseEntidad {

    private LocalDate fecha;
    private int numero;
    private Double descuento;
    private int total;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;
    public enum FormaPago {
        efectivo,
        mercadoPago,
        debito,
        credito
    }

}
