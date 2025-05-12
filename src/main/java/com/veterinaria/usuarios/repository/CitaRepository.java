package com.veterinaria.usuarios.repository;

import com.veterinaria.usuarios.model.Cita;
import com.veterinaria.usuarios.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMascota(Mascota mascota);
    List<Cita> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Cita> findByTipoCita(Cita.TipoCita tipoCita);
}
