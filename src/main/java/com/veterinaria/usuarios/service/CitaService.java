package com.veterinaria.usuarios.service;

import com.veterinaria.usuarios.dto.CitaDTO;
import com.veterinaria.usuarios.model.Cita;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<CitaDTO> findAll();
    Optional<CitaDTO> findById(Long id);
    List<CitaDTO> findByMascotaId(Long mascotaId);
    List<CitaDTO> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<CitaDTO> findByTipoCita(Cita.TipoCita tipoCita);
    CitaDTO save(CitaDTO citaDTO);
    CitaDTO update(Long id, CitaDTO citaDTO);
    void deleteById(Long id);
}
