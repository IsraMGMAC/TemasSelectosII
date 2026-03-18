package com.tienda.online.controllers;

import com.tienda.online.dto.ProductoRequest;
import com.tienda.online.dto.ProductoResponse;
import com.tienda.online.models.Producto;
import com.tienda.online.services.ProductoService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody ProductoRequest request) {
        try {
            Producto creado = productoService.insertarProducto(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(ProductoResponse.fromEntity(creado));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> obtenerTodos() {
        List<ProductoResponse> productos = productoService.obtenerTodos()
            .stream()
            .map(ProductoResponse::fromEntity)
            .toList();

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponse>> buscarPorNombre(@RequestParam String nombre) {
        List<ProductoResponse> productos = productoService.buscarPorNombre(nombre)
            .stream()
            .map(ProductoResponse::fromEntity)
            .toList();

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ProductoResponse>> obtenerActivos() {
        List<ProductoResponse> productos = productoService.obtenerActivos()
            .stream()
            .map(ProductoResponse::fromEntity)
            .toList();

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/precio")
    public ResponseEntity<List<ProductoResponse>> buscarPorPrecioMaximo(@RequestParam BigDecimal maximo) {
        List<ProductoResponse> productos = productoService.buscarPorPrecioMaximo(maximo)
            .stream()
            .map(ProductoResponse::fromEntity)
            .toList();

        return ResponseEntity.ok(productos);
    }
}
