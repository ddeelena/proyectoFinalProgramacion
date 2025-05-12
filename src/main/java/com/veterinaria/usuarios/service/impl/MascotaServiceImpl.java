package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.dto.MascotaDTO;
import com.veterinaria.usuarios.model.Mascota;
import com.veterinaria.usuarios.model.Usuario;
import com.veterinaria.usuarios.repository.MascotaRepository;
import com.veterinaria.usuarios.repository.UsuarioRepository;
import com.veterinaria.usuarios.service.MascotaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public MascotaServiceImpl(MascotaRepository mascotaRepository, UsuarioRepository usuarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MascotaDTO> findAll() {
        return mascotaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MascotaDTO> findById(Long id) {
        return mascotaRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MascotaDTO> findByPropietarioId(Long propietarioId) {
        Usuario propietario = usuarioRepository.findById(propietarioId)
                .orElseThrow(() -> new EntityNotFoundException("Propietario no encontrado con ID: " + propietarioId));

        return mascotaRepository.findByPropietario(propietario).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MascotaDTO save(MascotaDTO mascotaDTO) {
        Mascota mascota = convertToEntity(mascotaDTO);
        mascota = mascotaRepository.save(mascota);
        return convertToDTO(mascota);
    }

    @Override
    @Transactional
    public MascotaDTO update(Long id, MascotaDTO mascotaDTO) {
        if (!mascotaRepository.existsById(id)) {
            throw new EntityNotFoundException("Mascota no encontrada con ID: " + id);
        }

        Mascota mascota = convertToEntity(mascotaDTO);
        mascota.setId(id);
        mascota = mascotaRepository.save(mascota);
        return convertToDTO(mascota);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new EntityNotFoundException("Mascota no encontrada con ID: " + id);
        }
        mascotaRepository.deleteById(id);
    }

    private MascotaDTO convertToDTO(Mascota mascota) {
        MascotaDTO mascotaDTO = new MascotaDTO();
        mascotaDTO.setId(mascota.getId());
        mascotaDTO.setNombre(mascota.getNombre());
        mascotaDTO.setEspecie(mascota.getEspecie());
        mascotaDTO.setRaza(mascota.getRaza());
        mascotaDTO.setEdad(mascota.getEdad());
        mascotaDTO.setPeso(mascota.getPeso());
        mascotaDTO.setObservaciones(mascota.getObservaciones());
        mascotaDTO.setPropietarioId(mascota.getPropietario().getId());
        return mascotaDTO;
    }

    private Mascota convertToEntity(MascotaDTO mascotaDTO) {
        Mascota mascota = new Mascota();
        mascota.setId(mascotaDTO.getId());
        mascota.setNombre(mascotaDTO.getNombre());
        mascota.setEspecie(mascotaDTO.getEspecie());
        mascota.setRaza(mascotaDTO.getRaza());
        mascota.setEdad(mascotaDTO.getEdad());
        mascota.setPeso(mascotaDTO.getPeso());
        mascota.setObservaciones(mascotaDTO.getObservaciones());

        Usuario propietario = usuarioRepository.findById(mascotaDTO.getPropietarioId())
                .orElseThrow(() -> new EntityNotFoundException("Propietario no encontrado con ID: " + mascotaDTO.getPropietarioId()));
        mascota.setPropietario(propietario);

        return mascota;
    }
}