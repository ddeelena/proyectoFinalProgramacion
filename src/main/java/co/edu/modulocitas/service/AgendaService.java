package co.edu.modulocitas.service;

import co.edu.modulocitas.enums.Estado;
import co.edu.modulocitas.model.Cita;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AgendaService {

    Optional<Cita> consultarCita(Integer idCita);
    Cita crearCita(Cita cita);
    Optional<Cita> actualizarCita(int id, Cita cita);
    //Boolean consultarVeterinario(Integer idVeterinario);
    Optional<Cita> cambiarEstado(int id, Estado estado);

}
