package com.alquiler.sistema.alquiler_api.model.entity;

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
@Table(name = "categorias")
@SQLDelete(sql = "UPDATE categorias SET fecha_baja = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("fecha_baja IS NULL")
public class Categoria extends AuditableEntity {

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 150)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_alta_id", updatable = false)
    private UsuarioAdmin usuarioAlta;

    @OneToMany(mappedBy = "categoria")
    private List<Equipo> equipos = new ArrayList<>();
}
