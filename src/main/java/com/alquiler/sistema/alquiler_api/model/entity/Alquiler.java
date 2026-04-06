package com.alquiler.sistema.alquiler_api.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.alquiler.sistema.alquiler_api.model.enums.EstadoAlquiler;
import com.alquiler.sistema.alquiler_api.model.enums.TipoEntrega;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "alquileres")
@SQLDelete(sql = "UPDATE alquileres SET fecha_baja = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("fecha_baja IS NULL")
public class Alquiler extends AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "codigo_reserva", nullable = false, unique = true, length = 50)
    private String codigoReserva;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin_esperada", nullable = false)
    private LocalDateTime fechaFinEsperada;

    @Column(name = "fecha_devolucion_real")
    private LocalDateTime fechaDevolucionReal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoAlquiler estado = EstadoAlquiler.Pendiente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_entrega", nullable = false, length = 30)
    private TipoEntrega tipoEntrega = TipoEntrega.Recoleccion_Sucursal;

    @Column(name = "direccion_envio", columnDefinition = "TEXT")
    private String direccionEnvio;

    @Column(name = "costo_envio", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoEnvio = BigDecimal.ZERO;

    @Column(name = "total_renta", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalRenta = BigDecimal.ZERO;

    @Column(name = "deposito_garantia", nullable = false, precision = 10, scale = 2)
    private BigDecimal depositoGarantia = BigDecimal.ZERO;

    @Column(name = "notas_operativas", columnDefinition = "TEXT")
    private String notasOperativas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_reserva_id", nullable = false)
    private UsuarioAdmin usuarioReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_entrega_id")
    private UsuarioAdmin usuarioEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_devolucion_id")
    private UsuarioAdmin usuarioDevolucion;

    @OneToMany(mappedBy = "alquiler")
    private List<AlquilerDetalle> detalles = new ArrayList<>();
}
