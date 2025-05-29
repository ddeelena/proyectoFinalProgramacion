package co.edu.modulocitas.service;

import co.edu.modulocitas.enums.Estado;
import co.edu.modulocitas.model.Cita;
import java.util.Optional;

public interface AgendaService {

    Optional<Cita> consultarCitaPorId(Integer idCita);
    Cita crearCita(Cita cita);
    Optional<Cita> actualizarCita(int idCita, Cita cita);
    Optional<Cita> cambiarEstado(int idCita, Estado estado);
}
