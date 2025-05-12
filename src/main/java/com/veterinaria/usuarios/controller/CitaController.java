package com.veterinaria.usuarios.controller;

import com.veterinaria.usuarios.dto.CitaDTO;
import com.veterinaria.usuarios.model.Cita;
import com.veterinaria.usuarios.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    @Autowired
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<List<CitaDTO>> findAll() {
        return ResponseEntity.ok(citaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> findById(@PathVariable Long id) {
        return citaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<CitaDTO>> findByMascotaId(@PathVariable Long mascotaId) {
        return ResponseEntity.ok(citaService.findByMascotaId(mascotaId));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<CitaDTO>> findByFechaBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(citaService.findByFechaBetween(inicio, fin));
    }

    @GetMapping("/tipo/{tipoCita}")
    public ResponseEntity<List<CitaDTO>> findByTipoCita(@PathVariable Cita.TipoCita tipoCita) {
        return ResponseEntity.ok(citaService.findByTipoCita(tipoCita));
    }

    @PostMapping
    public ResponseEntity<CitaDTO> save(@Valid @RequestBody CitaDTO citaDTO) {
        if (citaDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            CitaDTO saved = citaService.save(citaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable Long id, @Valid @RequestBody CitaDTO citaDTO) {
        try {
            CitaDTO updated = citaService.update(id, citaDTO);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            citaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}