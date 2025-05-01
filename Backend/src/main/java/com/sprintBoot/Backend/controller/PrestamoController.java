

package com.sprintBoot.Backend.controller;


import com.sprintBoot.Backend.dto.PrestamoRequest;
import com.sprintBoot.Backend.model.Prestamo;
import com.sprintBoot.Backend.service.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    private final PrestamoService service;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearPrestamo(@RequestBody PrestamoRequest request) {
        Prestamo prestamo = service.crearPrestamo(request);
        return ResponseEntity.ok(Map.of(
                "id", prestamo.getId(),
                "fechaMaximaDevolucion", prestamo.getFechaMaximaDevolucion().format(formatter)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPrestamo(@PathVariable Long id) {
        Prestamo prestamo = service.obtenerPrestamo(id);
        return ResponseEntity.ok(Map.of(
                "id", prestamo.getId(),
                "isbn", prestamo.getIsbn(),
                "identificacionUsuario", prestamo.getIdentificacionUsuario(),
                "tipoUsuario", prestamo.getTipoUsuario(),
                "fechaMaximaDevolucion", prestamo.getFechaMaximaDevolucion().format(formatter)
        ));
    }
}