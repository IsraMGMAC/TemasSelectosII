package com.jugadores.jugadores_barcelona.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private Integer idJugador;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "posicion")
    private String posicion;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Gol> goles;

    // Getters y Setters
    public Integer getIdJugador() { return idJugador; }
    public void setIdJugador(Integer idJugador) { this.idJugador = idJugador; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public List<Gol> getGoles() { return goles; }
    public void setGoles(List<Gol> goles) { this.goles = goles; }
}