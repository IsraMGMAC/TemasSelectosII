package com.jugadores.jugadores_barcelona.repository;


import com.jugadores.jugadores_barcelona.entity.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
    List<Jugador> findByNacionalidadIgnoreCase(String nacionalidad);
}