package co.edu.modulocitas.service.impl;


import co.edu.modulocitas.enums.Estado;
import co.edu.modulocitas.model.Cita;
import co.edu.modulocitas.repository.CitaRepository;
import co.edu.modulocitas.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AgendaServiceImpl implements AgendaService {

    private final CitaRepository citaRepository;

    @Override
    public Optional<Cita> consultarCitaPorId(Integer idCita){
        return citaRepository.findById(idCita);
    }

    @Override
    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public Optional<Cita> actualizarCita(int idCita, Cita cita) {
        return citaRepository.findById(idCita)
                .map(existingCita -> {
                    existingCita.setFecha(cita.getFecha());
                    existingCita.setHora( cita.getHora());
                    existingCita.setEstado(cita.getEstado());
                    existingCita.setEsUrgencia(cita.isEsUrgencia());
                    existingCita.setIdPaciente(cita.getIdPaciente());
                    return citaRepository.save(existingCita);
                });
    }

    @Override
    public Optional<Cita> cambiarEstado(int idCita, Estado estado) {
        return citaRepository.findById(idCita)
                .map( existingCita ->{
                    existingCita.setEstado(estado);
                    return citaRepository.save(existingCita);
                });
    }


}
//@Override
//public Boolean consultarVeterinario(Integer idVeterinario) {
//  return null;
//}
