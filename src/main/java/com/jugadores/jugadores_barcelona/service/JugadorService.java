package com.jugadores.jugadores_barcelona.service;

import com.jugadores.jugadores_barcelona.entity.Gol;
import com.jugadores.jugadores_barcelona.entity.Jugador;
import com.jugadores.jugadores_barcelona.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository repository;

    public List<Jugador> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Jugador> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    public List<Jugador> obtenerPorNacionalidad(String nacionalidad) {
        return repository.findByNacionalidadIgnoreCase(nacionalidad);
    }

    public Jugador guardarJugador(Jugador jugador) {
        return repository.save(jugador);
    }

    public Jugador actualizarJugador(Integer id, Jugador detallesJugador) {
        return repository.findById(id).map(jugador -> {
            jugador.setNombre(detallesJugador.getNombre());
            jugador.setPosicion(detallesJugador.getPosicion());
            jugador.setNacionalidad(detallesJugador.getNacionalidad());
            return repository.save(jugador);
        }).orElseThrow(() -> new RuntimeException("Jugador no encontrado con ID: " + id));
    }

    public List<Gol> obtenerGolesPorJugadorId(Integer id) {
        return repository.findById(id)
            .map(Jugador::getGoles)
            .orElseThrow(() -> new RuntimeException("Jugador no encontrado con ID: " + id));
    }
}