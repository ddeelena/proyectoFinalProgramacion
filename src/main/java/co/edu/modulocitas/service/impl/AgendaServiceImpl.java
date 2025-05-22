package co.edu.modulocitas.service.impl;


import co.edu.modulocitas.enums.Estado;
import co.edu.modulocitas.model.Cita;
import co.edu.modulocitas.repository.CitaRepository;
import co.edu.modulocitas.service.AgendaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgendaServiceImpl implements AgendaService {

    @Autowired
    private final CitaRepository citaRepository;


    @Override
    public Optional<Cita> consultarCita(Integer idCita) {
        return citaRepository.findById(idCita);
    }




    @Override
    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public Optional<Cita> actualizarCita(int id, Cita cita) {
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
    public Optional<Cita> cambiarEstado(int id, Estado estado) {
        return citaRepository.findById(id)
                .map( existingCita ->{
                    existingCita.setEstado(estado);
                    return citaRepository.save(existingCita);
                });
    }


    //@Override
    //public Boolean consultarVeterinario(Integer idVeterinario) {
    //  return null;
    //}
}
