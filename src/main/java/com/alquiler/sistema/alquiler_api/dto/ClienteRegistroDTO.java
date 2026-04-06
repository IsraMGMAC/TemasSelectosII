package com.alquiler.sistema.alquiler_api.dto;

import lombok.Data;

@Data
public class ClienteRegistroDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String email;
    private String direccion;
}