package com.jugadores.jugadores_barcelona.controller;



import com.jugadores.jugadores_barcelona.entity.Gol;
import com.jugadores.jugadores_barcelona.entity.Jugador;
import com.jugadores.jugadores_barcelona.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JugadorController {

    @Autowired
    private JugadorService service;

    // 0. GET: Página de inicio con documentación de las APIs
    @GetMapping("/")
    public String inicio() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>API Jugadores Barcelona</title>
                    <style>
                        body { font-family: Arial; padding: 20px; background-color: #004d98; color: white; }
                        .container { max-width: 800px; margin: 0 auto; }
                        .form-section { background: rgba(255,255,255,0.1); padding: 20px; margin: 20px 0; border-radius: 8px; }
                        input, button { padding: 10px; margin: 5px 0; border-radius: 4px; border: none; }
                        input { width: 200px; }
                        button { background: #ffcc00; color: #004d98; font-weight: bold; cursor: pointer; }
                        button:hover { background: #ffd700; }
                        #resultado { background: rgba(0,0,0,0.3); padding: 15px; margin-top: 10px; border-radius: 4px; overflow-x: auto; }
                        a { color: #ffcc00; text-decoration: none; }
                        a:hover { text-decoration: underline; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>⚽ API REST - Jugadores del FC Barcelona</h1>
                        
                        <h2>Enlaces rápidos GET:</h2>
                        <ul>
                            <li><a href='/api/jugadores'>/api/jugadores</a> - Ver todos los jugadores</li>
                            <li><a href='/api/jugadores/1'>/api/jugadores/1</a> - Ver jugador con ID 1</li>
                            <li><a href='/api/jugadores/1/goles'>/api/jugadores/1/goles</a> - Ver goles de un jugador</li>
                            <li><a href='/api/jugadores/nacionalidad/Argentina'>/api/jugadores/nacionalidad/Argentina</a> - Jugadores por nacionalidad</li>
                        </ul>
                        
                        <div class="form-section">
                            <h3>📝 POST - Crear nuevo jugador</h3>
                            <input type="text" id="nombre" placeholder="Nombre" value="Gavi"><br>
                            <input type="text" id="posicion" placeholder="Posición" value="Centrocampista"><br>
                            <input type="text" id="nacionalidad" placeholder="Nacionalidad" value="España"><br>
                            <button onclick="crearJugador()">Crear Jugador</button>
                            <div id="resultado-post"></div>
                        </div>
                        
                        <div class="form-section">
                            <h3>✏️ PUT - Actualizar jugador</h3>
                            <input type="number" id="put-id" placeholder="ID del jugador" value="1"><br>
                            <input type="text" id="put-nombre" placeholder="Nuevo nombre" value="Lionel Messi"><br>
                            <input type="text" id="put-posicion" placeholder="Nueva posición" value="Delantero"><br>
                            <input type="text" id="put-nacionalidad" placeholder="Nueva nacionalidad" value="Argentina"><br>
                            <button onclick="actualizarJugador()">Actualizar Jugador</button>
                            <div id="resultado-put"></div>
                        </div>
                    </div>
                    
                    <script>
                        async function crearJugador() {
                            const data = {
                                nombre: document.getElementById('nombre').value,
                                posicion: document.getElementById('posicion').value,
                                nacionalidad: document.getElementById('nacionalidad').value
                            };
                            
                            try {
                                const response = await fetch('/api/jugadores', {
                                    method: 'POST',
                                    headers: {'Content-Type': 'application/json'},
                                    body: JSON.stringify(data)
                                });
                                const result = await response.json();
                                document.getElementById('resultado-post').innerHTML = 
                                    '<div id="resultado">Jugador creado:<br>' + JSON.stringify(result, null, 2) + '</div>';
                            } catch (error) {
                                document.getElementById('resultado-post').innerHTML = 
                                    '<div id="resultado">Error: ' + error.message + '</div>';
                            }
                        }
                        
                        async function actualizarJugador() {
                            const id = document.getElementById('put-id').value;
                            const data = {
                                nombre: document.getElementById('put-nombre').value,
                                posicion: document.getElementById('put-posicion').value,
                                nacionalidad: document.getElementById('put-nacionalidad').value
                            };
                            
                            try {
                                const response = await fetch('/api/jugadores/' + id, {
                                    method: 'PUT',
                                    headers: {'Content-Type': 'application/json'},
                                    body: JSON.stringify(data)
                                });
                                const result = await response.json();
                                document.getElementById('resultado-put').innerHTML = 
                                    '<div id="resultado"> Jugador actualizado:<br>' + JSON.stringify(result, null, 2) + '</div>';
                            } catch (error) {
                                document.getElementById('resultado-put').innerHTML = 
                                    '<div id="resultado"> Error: ' + error.message + '</div>';
                            }
                        }
                    </script>
                </body>
                </html>
                """;
    }

    // 1. GET: Todos los jugadores
    @GetMapping("/api/jugadores")
    public List<Jugador> obtenerTodos() {
        return service.obtenerTodos();
    }

    // 2. GET: Jugador por ID
    @GetMapping("/api/jugadores/{id}")
    public ResponseEntity<Jugador> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. GET: Jugadores por Nacionalidad (Ej: /api/jugadores/nacionalidad/Argentina)
    @GetMapping("/api/jugadores/nacionalidad/{nacionalidad}")
    public List<Jugador> obtenerPorNacionalidad(@PathVariable String nacionalidad) {
        return service.obtenerPorNacionalidad(nacionalidad);
    }

    // 4. POST: Crear un nuevo jugador
    @PostMapping("/api/jugadores")
    public Jugador crearJugador(@RequestBody Jugador jugador) {
        return service.guardarJugador(jugador);
    }

    // 5. PUT: Actualizar un jugador existente
    @PutMapping("/api/jugadores/{id}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable Integer id, @RequestBody Jugador detallesJugador) {
        try {
            Jugador jugadorActualizado = service.actualizarJugador(id, detallesJugador);
            return ResponseEntity.ok(jugadorActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 6. GET: Obtener todos los goles de un jugador por su ID
    @GetMapping("/api/jugadores/{id}/goles")
    public ResponseEntity<List<Gol>> obtenerGolesPorJugadorId(@PathVariable Integer id) {
        try {
            List<Gol> goles = service.obtenerGolesPorJugadorId(id);
            return ResponseEntity.ok(goles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
