package com.veterinaria.usuarios.repository;

import com.veterinaria.usuarios.model.Mascota;
import com.veterinaria.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByPropietario(Usuario propietario);
}