package com.tienda.online.services;

import com.tienda.online.dto.ProductoRequest;
import com.tienda.online.models.Producto;
import com.tienda.online.repositories.ProductoRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto insertarProducto(ProductoRequest request) {
        if (request.getNombre() == null || request.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (request.getPrecio() == null || request.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
        }

        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock() == null ? 0 : request.getStock());
        producto.setActivo(request.getActivo() == null ? Boolean.TRUE : request.getActivo());

        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> obtenerActivos() {
        return productoRepository.findByActivoTrue();
    }

    public List<Producto> buscarPorPrecioMaximo(BigDecimal precioMaximo) {
        return productoRepository.findByPrecioLessThanEqual(precioMaximo);
    }
}
