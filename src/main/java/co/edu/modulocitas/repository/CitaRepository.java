package co.edu.modulocitas.repository;

import co.edu.modulocitas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;


public interface CitaRepository extends JpaRepository<Cita, Integer> {
    Optional<Cita> findByMotivo(String motivo);
}
