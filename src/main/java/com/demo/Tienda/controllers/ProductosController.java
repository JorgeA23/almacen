package com.demo.Tienda.controllers;

import com.demo.Tienda.entities.ProductosEntity;
import com.demo.Tienda.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductosController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProducto(@PathVariable UUID id) {
        return productoService.getProductoById(id);
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody ProductosEntity producto) {
        return productoService.createProducto(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProducto(@PathVariable UUID id, @RequestBody ProductosEntity producto) {
        return productoService.updateProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProducto(@PathVariable UUID id) {
        return productoService.deleteProducto(id);
    }
}
