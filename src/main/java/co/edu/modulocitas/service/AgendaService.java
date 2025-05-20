package co.edu.modulocitas.service;

import co.edu.modulocitas.model.Cita;

public interface AgendaService {

    Boolean consultarCita(Integer idCita);
    Cita crerCita(Cita cita);
    Cita actualizarCita(Cita cita);
    Boolean consultarVeterinario(Integer idVeterinario);

}
