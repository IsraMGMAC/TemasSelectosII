package com.alquiler.sistema.alquiler_api.service;

import com.alquiler.sistema.alquiler_api.model.entity.Cliente;
import com.alquiler.sistema.alquiler_api.repository.ClienteRepository;
import com.alquiler.sistema.alquiler_api.dto.ClienteRegistroDTO;
import com.alquiler.sistema.alquiler_api.dto.ClienteResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public ClienteResponseDTO registrarNuevoCliente(ClienteRegistroDTO dto) {
        // DTO Entrada -> Entidad
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellidoPaterno(dto.getApellidoPaterno());
        cliente.setApellidoMaterno(dto.getApellidoMaterno());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEmail(dto.getEmail());
        cliente.setDireccion(dto.getDireccion());

        // Guardar en BD
        Cliente guardado = clienteRepository.save(cliente);

        // Enviar Correo
        emailService.enviarConfirmacionRegistro(guardado.getEmail(), guardado.getNombre());

        return toResponseDTO(guardado);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {
        ClienteResponseDTO respuesta = new ClienteResponseDTO();
        respuesta.setId(cliente.getId());
        respuesta.setNombre(cliente.getNombre());
        respuesta.setApellidoPaterno(cliente.getApellidoPaterno());
        respuesta.setApellidoMaterno(cliente.getApellidoMaterno());
        respuesta.setEmail(cliente.getEmail());
        respuesta.setTelefono(cliente.getTelefono());
        respuesta.setFechaAlta(cliente.getFechaAlta());
        return respuesta;
    }
}