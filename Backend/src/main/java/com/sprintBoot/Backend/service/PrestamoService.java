package com.sprintBoot.Backend.service;

import com.sprintBoot.Backend.dto.PrestamoRequest;
import com.sprintBoot.Backend.exception.PrestamoNoEncontradoException;
import com.sprintBoot.Backend.exception.TipoUsuarioNoPermitidoException;
import com.sprintBoot.Backend.exception.UsuarioInvitadoYaTienePrestamoException;
import com.sprintBoot.Backend.model.Prestamo;
import com.sprintBoot.Backend.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class PrestamoService {

    private final PrestamoRepository repository;

    public PrestamoService(PrestamoRepository repository) {
        this.repository = repository;
    }

    public Prestamo crearPrestamo(PrestamoRequest request) {
        validarTipoUsuario(request.getTipoUsuario());

        if (request.getTipoUsuario() == 3 && repository.findByIdentificacionUsuario(request.getIdentificacionUsuario()).isPresent()) {
            throw new UsuarioInvitadoYaTienePrestamoException(request.getIdentificacionUsuario());
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setIsbn(request.getIsbn());
        prestamo.setIdentificacionUsuario(request.getIdentificacionUsuario());
        prestamo.setTipoUsuario(request.getTipoUsuario());
        prestamo.setFechaMaximaDevolucion(calcularFechaDevolucion(request.getTipoUsuario()));

        return repository.save(prestamo);
    }

    public Prestamo obtenerPrestamo(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontradoException(id));
    }

    private void validarTipoUsuario(int tipo) {
        if (tipo < 1 || tipo > 3) {
            throw new TipoUsuarioNoPermitidoException();
        }
    }

    private LocalDate calcularFechaDevolucion(int tipoUsuario) {
        int diasHabiles = switch (tipoUsuario) {
            case 1 -> 10;
            case 2 -> 8;
            default -> 7;
        };

        LocalDate fecha = LocalDate.now();
        while (diasHabiles > 0) {
            fecha = fecha.plusDays(1);
            if (!(fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                diasHabiles--;
            }
        }
        return fecha;
    }
}
