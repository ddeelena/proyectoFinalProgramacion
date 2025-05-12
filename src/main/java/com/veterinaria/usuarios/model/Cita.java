package com.veterinaria.usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    @Future(message = "La fecha debe ser futura")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo de cita es obligatorio")
    private TipoCita tipoCita;

    private String motivo;

    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    public enum TipoCita {
        CONSULTA_GENERAL,
        CIRUGIA,
        ESTETICA,
        VACUNACION,
        CHEQUEO_RUTINA
    }
}