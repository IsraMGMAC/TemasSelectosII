package com.alquiler.sistema.alquiler_api.controller;

import com.alquiler.sistema.alquiler_api.service.ClienteService;
import com.alquiler.sistema.alquiler_api.dto.ClienteRegistroDTO;
import com.alquiler.sistema.alquiler_api.dto.ClienteResponseDTO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/registrar")
    public ResponseEntity<ClienteResponseDTO> registrar(@RequestBody ClienteRegistroDTO dto) {
        ClienteResponseDTO respuesta = clienteService.registrarNuevoCliente(dto);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> obtenerTodos() {
        List<ClienteResponseDTO> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }
}