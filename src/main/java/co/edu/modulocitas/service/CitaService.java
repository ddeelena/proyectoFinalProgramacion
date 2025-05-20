package co.edu.modulocitas.service;

import co.edu.modulocitas.model.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<Cita> getAllCitas();
    Optional<Cita> getCita(int id);
    Cita saveCita(Cita cita);
    Optional<Cita> updateCita( int id,Cita cita);
    Optional<Cita> deleteCita(int id);
    Optional<Cita> getMotivoConsulta(String motivoConsulta);

}
