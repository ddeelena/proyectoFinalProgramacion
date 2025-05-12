package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.dto.UsuarioDTO;
import com.veterinaria.usuarios.model.Usuario;
import com.veterinaria.usuarios.repository.UsuarioRepository;
import com.veterinaria.usuarios.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    @Transactional
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return convertToDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }

        Usuario usuario = convertToEntity(usuarioDTO);
        usuario.setId(id);
        usuario = usuarioRepository.save(usuario);
        return convertToDTO(usuario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(this::convertToDTO);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setDireccion(usuario.getDireccion());
        return usuarioDTO;
    }

    private Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setDireccion(usuarioDTO.getDireccion());
        return usuario;
    }
}