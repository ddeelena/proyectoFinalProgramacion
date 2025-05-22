package co.edu.modulocitas.service;

import co.edu.modulocitas.model.Cita;
import co.edu.modulocitas.model.HistoriaClinica;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HistoriaClinicaService {
    Optional<HistoriaClinica> consultarHistoria(Integer idCita);
}
