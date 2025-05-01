package com.sprintBoot.Backend.repository;

import com.sprintBoot.Backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Métodos personalizados si es necesario
}
