package com.alquiler.sistema.alquiler_api.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.alquiler.sistema.alquiler_api.model.enums.RolUsuarioAdmin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios_admin")
@SQLDelete(sql = "UPDATE usuarios_admin SET fecha_baja = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("fecha_baja IS NULL")
public class UsuarioAdmin extends AuditableEntity {

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 100)
    private String apellidoMaterno;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RolUsuarioAdmin rol = RolUsuarioAdmin.Operador_Inventario;

    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso;

    @OneToMany(mappedBy = "usuarioAlta")
    private List<Categoria> categoriasCreadas = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioAlta")
    private List<Equipo> equiposCreados = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioReserva")
    private List<Alquiler> reservasCreadas = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioEntrega")
    private List<Alquiler> entregasRegistradas = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioDevolucion")
    private List<Alquiler> devolucionesRegistradas = new ArrayList<>();
}
