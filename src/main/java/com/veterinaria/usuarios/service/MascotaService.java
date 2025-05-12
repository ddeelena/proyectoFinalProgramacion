package com.veterinaria.usuarios.service;

import com.veterinaria.usuarios.dto.MascotaDTO;

import java.util.List;
import java.util.Optional;

public interface MascotaService {
    List<MascotaDTO> findAll();
    Optional<MascotaDTO> findById(Long id);
    List<MascotaDTO> findByPropietarioId(Long propietarioId);
    MascotaDTO save(MascotaDTO mascotaDTO);
    MascotaDTO update(Long id, MascotaDTO mascotaDTO);
    void deleteById(Long id);
}