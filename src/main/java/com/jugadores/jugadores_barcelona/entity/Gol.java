package com.jugadores.jugadores_barcelona.entity; 

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "goles")
public class Gol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gol")
    private Integer idGol;

    @Column(name = "rival")
    private String rival;

    @Column(name = "estadio")
    private String estadio;

    @Column(name = "condicion")
    private String condicion;

    @Column(name = "fecha_gol")
    private LocalDate fechaGol;

    // Relación Muchos a Uno: Muchos goles pertenecen a Un jugador
    @ManyToOne
    @JoinColumn(name = "id_jugador", nullable = false)
    @JsonIgnore // MUY IMPORTANTE: Evita un bucle infinito al generar el JSON
    private Jugador jugador;

    // Getters y Setters
    public Integer getIdGol() { return idGol; }
    public void setIdGol(Integer idGol) { this.idGol = idGol; }
    public String getRival() { return rival; }
    public void setRival(String rival) { this.rival = rival; }
    public String getEstadio() { return estadio; }
    public void setEstadio(String estadio) { this.estadio = estadio; }
    public String getCondicion() { return condicion; }
    public void setCondicion(String condicion) { this.condicion = condicion; }
    public LocalDate getFechaGol() { return fechaGol; }
    public void setFechaGol(LocalDate fechaGol) { this.fechaGol = fechaGol; }
    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
}