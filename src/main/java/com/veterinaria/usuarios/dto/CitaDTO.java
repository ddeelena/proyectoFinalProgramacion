package com.veterinaria.usuarios.dto;

import com.veterinaria.usuarios.model.Cita;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaDTO {
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    @Future(message = "La fecha debe ser futura")
    private LocalDateTime fecha;

    @NotNull(message = "El tipo de cita es obligatorio")
    private Cita.TipoCita tipoCita;

    private String motivo;

    private String observaciones;

    @NotNull(message = "El ID de la mascota es obligatorio")
    private Long mascotaId;
}