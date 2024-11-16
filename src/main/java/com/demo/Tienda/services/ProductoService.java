package com.demo.Tienda.services;

import com.demo.Tienda.entities.ProductosEntity;
import com.demo.Tienda.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public ResponseEntity<Map<String, Object>> getAllProductos() {
        Map<String, Object> response = new HashMap<>();
        List<ProductosEntity> productos = productoRepository.findAll();
        response.put("Productos", productos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getProductoById(UUID id) {
        Map<String, Object>response = new HashMap<>();
        Optional<ProductosEntity> productoFound = productoRepository.findById(id);
        if (productoFound.isPresent()) {
            response.put("Producto", productoFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Error", "Producto no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createProducto(ProductosEntity producto) {
        Map<String, Object> response = new HashMap<>();
        producto.setId(UUID.randomUUID());
        if (productoRepository.existsById(producto.getId())) {
            response.put("Error", "El producto ya existe");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            ProductosEntity newProducto = productoRepository.save(producto);
            response.put("Estado", "Articulo agregado Correctamente");
            response.put("Producto", newProducto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateProducto(UUID id, ProductosEntity producto) {
        Map<String, Object> response = new HashMap<>();
        Optional<ProductosEntity> productoFound = productoRepository.findById(id);
        if (productoFound.isPresent()) {
            ProductosEntity existingProducto = productoFound.get();
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setDescripcion(producto.getDescripcion());
            existingProducto.setPrecio(producto.getPrecio());
            productoRepository.save(existingProducto);
            response.put("Estado", "Producto actualizado Correctamente");
            response.put("UpdatedCountry", existingProducto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Estado", "Producto no encongtrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteProducto(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<ProductosEntity> productoFound = productoRepository.findById(id);
        if (productoFound.isPresent()) {
            productoRepository.deleteById(id);
            response.put("Estado", "Producto eliminado Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Estado", "Producto no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
