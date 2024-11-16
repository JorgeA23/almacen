package com.demo.Tienda.repositories;

import com.demo.Tienda.entities.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<ProductosEntity, UUID> {
}
