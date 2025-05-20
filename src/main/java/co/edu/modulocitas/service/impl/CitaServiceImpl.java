package co.edu.modulocitas.service.impl;

import co.edu.modulocitas.model.Cita;
import co.edu.modulocitas.repository.CitaRepository;
import co.edu.modulocitas.service.CitaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    @Override
    public List<Cita> getAllCitas() {
        return citaRepository.findAll() ;
    }

    @Override
    public Optional<Cita> getCita(int id) {
        return citaRepository.findById(id);
    }

    @Override
    public Cita saveCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public Optional<Cita> updateCita(int id,Cita cita) {
        return citaRepository.findById(id)
                .map(existingCita -> {
                    existingCita.setFecha(cita.getFecha());
                    existingCita.setHora( cita.getHora());
                    existingCita.setEstado(cita.getEstado());
                    existingCita.setEs_urgencia(cita.isEs_urgencia());
                    existingCita.setIdPaciente(cita.getIdPaciente());
                    return citaRepository.save(existingCita);
                });
    }

    @Override
    public Optional<Cita> deleteCita(int id) {
        return citaRepository.findById(id)
                .map(cita -> {
                    citaRepository.delete(cita);
                    return cita;
                });

    }

    @Override
    public Optional<Cita> getMotivoConsulta(String motivoConsulta) {
        return citaRepository.findByMotivo(motivoConsulta);
    }
}
