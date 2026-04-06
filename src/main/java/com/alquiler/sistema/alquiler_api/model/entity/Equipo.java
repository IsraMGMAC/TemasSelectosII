package com.alquiler.sistema.alquiler_api.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "equipos")
@SQLDelete(sql = "UPDATE equipos SET fecha_baja = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("fecha_baja IS NULL")
public class Equipo extends AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, unique = true, length = 150)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "cantidad_optima", nullable = false)
    private Integer cantidadOptima = 0;

    @Column(name = "cantidad_con_detalles", nullable = false)
    private Integer cantidadConDetalles = 0;

    @Column(name = "cantidad_mantenimiento", nullable = false)
    private Integer cantidadMantenimiento = 0;

    @Column(name = "cantidad_inservible", nullable = false)
    private Integer cantidadInservible = 0;

    @Column(name = "cantidad_total_rentable", insertable = false, updatable = false)
    private Integer cantidadTotalRentable;

    @Column(name = "cantidad_total_fisica", insertable = false, updatable = false)
    private Integer cantidadTotalFisica;

    @Column(name = "precio_renta_dia", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioRentaDia = BigDecimal.ZERO;

    @Column(name = "visible_web", nullable = false)
    private Boolean visibleWeb = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_alta_id", updatable = false)
    private UsuarioAdmin usuarioAlta;

    @OneToMany(mappedBy = "equipo")
    private List<ImagenEquipo> imagenes = new ArrayList<>();

    @OneToMany(mappedBy = "equipo")
    private List<AlquilerDetalle> detallesAlquiler = new ArrayList<>();
}
