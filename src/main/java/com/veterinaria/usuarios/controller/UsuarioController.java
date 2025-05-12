package com.veterinaria.usuarios.controller;

import com.veterinaria.usuarios.dto.UsuarioDTO;
import com.veterinaria.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email) {
        return usuarioService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Continuación de UsuarioController.java

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        if (usuarioService.existsByEmail(usuarioDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        UsuarioDTO saved = usuarioService.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO updated = usuarioService.update(id, usuarioDTO);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}