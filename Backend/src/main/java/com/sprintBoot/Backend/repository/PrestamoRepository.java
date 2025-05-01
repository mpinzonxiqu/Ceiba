package com.sprintBoot.Backend.repository;

import com.sprintBoot.Backend.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    Optional<Prestamo> findByIdentificacionUsuario(String identificacionUsuario);
}

