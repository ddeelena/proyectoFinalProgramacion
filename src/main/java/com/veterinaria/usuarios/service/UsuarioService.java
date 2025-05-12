package com.veterinaria.usuarios.service;

import com.veterinaria.usuarios.dto.UsuarioDTO;
import com.veterinaria.usuarios.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDTO> findAll();
    Optional<UsuarioDTO> findById(Long id);
    UsuarioDTO save(UsuarioDTO usuarioDTO);
    UsuarioDTO update(Long id, UsuarioDTO usuarioDTO);
    void deleteById(Long id);
    boolean existsByEmail(String email);
    Optional<UsuarioDTO> findByEmail(String email);
}