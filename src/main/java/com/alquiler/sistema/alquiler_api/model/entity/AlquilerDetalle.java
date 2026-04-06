package com.alquiler.sistema.alquiler_api.model.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alquiler_detalles")
@SQLDelete(sql = "UPDATE alquiler_detalles SET fecha_baja = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("fecha_baja IS NULL")
public class AlquilerDetalle extends AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alquiler_id", nullable = false)
    private Alquiler alquiler;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipo_id", nullable = false)
    private Equipo equipo;

    @Column(name = "cantidad_rentada", nullable = false, updatable = false)
    private Integer cantidadRentada;

    @Column(name = "cantidad_devuelta_bien", nullable = false)
    private Integer cantidadDevueltaBien = 0;

    @Column(name = "cantidad_danada", nullable = false)
    private Integer cantidadDanada = 0;

    @Column(name = "cantidad_perdida", nullable = false)
    private Integer cantidadPerdida = 0;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2, updatable = false)
    private BigDecimal precioUnitario;

    @Column(nullable = false, precision = 10, scale = 2, updatable = false)
    private BigDecimal subtotal;
}
