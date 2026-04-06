package com.alquiler.sistema.alquiler_api.model.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clientes")
@SQLDelete(sql = "UPDATE clientes SET fecha_baja = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("fecha_baja IS NULL")
public class Cliente extends AuditableEntity {

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 100)
    private String apellidoMaterno;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(unique = true, length = 100)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String direccion;

    @OneToMany(mappedBy = "cliente")
    private List<Alquiler> alquileres = new ArrayList<>();
}
