package com.sprintBoot.Backend.controller;

import com.sprintBoot.Backend.model.Product;
import com.sprintBoot.Backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 1. Obtener la lista completa de productos (GET)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Si no hay productos, devolvemos un 204 (Sin contenido)
        }
        return ResponseEntity.ok(products); // Si hay productos, los devolvemos con un 200
    }

    // 2. Obtener un producto por su ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            // Si el producto no existe, devolvemos un 404 (No encontrado)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(product.get()); // Si el producto existe, lo devolvemos con un 200
    }

    // 3. Crear un nuevo producto (POST)
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (product == null || product.getName() == null || product.getPrice() == null) {
            // Validación simple: si los datos del producto son inválidos, devolvemos un 400 (Bad Request)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct); // Si todo está bien, devolvemos un 201 (Creado)
    }

    // 4. Crear varios productos de una vez (POST - Varios productos)
    @PostMapping("/bulk")
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
        if (products == null || products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Si la lista está vacía, devolvemos un 400
        }
        List<Product> savedProducts = productRepository.saveAll(products);  // Guarda todos los productos
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProducts); // Devuelve la lista de productos guardados con un 201
    }

    // 5. Actualizar la información de un producto (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!productRepository.existsById(id)) {
            // Si el producto no existe, devolvemos un 404 (No encontrado)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        product.setId(id); // Asignamos el ID al producto para asegurarnos de que estamos actualizando el correcto
        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct); // Devolvemos el producto actualizado con un 200 (OK)
    }

    // 6. Eliminar un producto (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            // Si el producto no existe, devolvemos un 404 (No encontrado)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
        productRepository.deleteById(id);
        // Respuesta con mensaje personalizado de éxito
        return ResponseEntity.status(HttpStatus.OK).body("Producto eliminado exitosamente"); // Mensaje de éxito con un 200 (OK)
    }
}
