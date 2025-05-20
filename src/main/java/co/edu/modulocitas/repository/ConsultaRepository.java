package co.edu.modulocitas.repository;

import co.edu.modulocitas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    Optional<Consulta> findConsultaByIdCita(int idCita);

    Optional<Consulta> findByMotivoConsulta(String motivoConsulta);
}
