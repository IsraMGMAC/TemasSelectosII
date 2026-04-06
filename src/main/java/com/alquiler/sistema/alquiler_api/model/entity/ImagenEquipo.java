package com.alquiler.sistema.alquiler_api.model.entity;

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
@Table(name = "imagenes_equipo")
@SQLDelete(sql = "UPDATE imagenes_equipo SET fecha_baja = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("fecha_baja IS NULL")
public class ImagenEquipo extends AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipo_id", nullable = false)
    private Equipo equipo;

    @Column(name = "url_imagen", nullable = false, length = 500)
    private String urlImagen;

    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal = false;
}
