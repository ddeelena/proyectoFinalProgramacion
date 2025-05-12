package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.dto.CitaDTO;
import com.veterinaria.usuarios.model.Cita;
import com.veterinaria.usuarios.model.Mascota;
import com.veterinaria.usuarios.repository.CitaRepository;
import com.veterinaria.usuarios.repository.MascotaRepository;
import com.veterinaria.usuarios.service.CitaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;

    @Autowired
    public CitaServiceImpl(CitaRepository citaRepository, MascotaRepository mascotaRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaDTO> findAll() {
        return citaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CitaDTO> findById(Long id) {
        return citaRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaDTO> findByMascotaId(Long mascotaId) {
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con ID: " + mascotaId));

        return citaRepository.findByMascota(mascota).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaDTO> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin) {
        return citaRepository.findByFechaBetween(inicio, fin).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaDTO> findByTipoCita(Cita.TipoCita tipoCita) {
        return citaRepository.findByTipoCita(tipoCita).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CitaDTO save(CitaDTO citaDTO) {
        Cita cita = convertToEntity(citaDTO);
        cita = citaRepository.save(cita);
        return convertToDTO(cita);
    }

    @Override
    @Transactional
    public CitaDTO update(Long id, CitaDTO citaDTO) {
        if (!citaRepository.existsById(id)) {
            throw new EntityNotFoundException("Cita no encontrada con ID: " + id);
        }

        Cita cita = convertToEntity(citaDTO);
        cita.setId(id);
        cita = citaRepository.save(cita);
        return convertToDTO(cita);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new EntityNotFoundException("Cita no encontrada con ID: " + id);
        }
        citaRepository.deleteById(id);
    }

    private CitaDTO convertToDTO(Cita cita) {
        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setId(cita.getId());
        citaDTO.setFecha(cita.getFecha());
        citaDTO.setTipoCita(cita.getTipoCita());
        citaDTO.setMotivo(cita.getMotivo());
        citaDTO.setObservaciones(cita.getObservaciones());
        citaDTO.setMascotaId(cita.getMascota().getId());
        return citaDTO;
    }

    private Cita convertToEntity(CitaDTO citaDTO) {
        Cita cita = new Cita();
        cita.setId(citaDTO.getId());
        cita.setFecha(citaDTO.getFecha());
        cita.setTipoCita(citaDTO.getTipoCita());
        cita.setMotivo(citaDTO.getMotivo());
        cita.setObservaciones(citaDTO.getObservaciones());

        Mascota mascota = mascotaRepository.findById(citaDTO.getMascotaId())
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con ID: " + citaDTO.getMascotaId()));
        cita.setMascota(mascota);

        return cita;
    }
}