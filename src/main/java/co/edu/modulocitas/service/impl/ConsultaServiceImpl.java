package co.edu.modulocitas.service.impl;

import co.edu.modulocitas.model.Consulta;
import co.edu.modulocitas.repository.CitaRepository;
import co.edu.modulocitas.repository.ConsultaRepository;
import co.edu.modulocitas.service.ConsultaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;

    @Override
    public List<Consulta> getConsultas() {
        return consultaRepository.findAll();
    }

    @Override
    public Optional<Consulta> getConsulta(int id) {
        return consultaRepository.findById(id);
    }

    @Override
    public Consulta saveConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Override
    public Optional<Consulta> updateConsulta(Consulta consulta) {
        return consultaRepository.findById(consulta.getId())
                .map(existingConsulta -> {
                    existingConsulta.setMotivoConsulta(consulta.getMotivoConsulta());
                    existingConsulta.setMotivoConsulta(consulta.getMotivoConsulta());
                    existingConsulta.setDiagnostico(consulta.getDiagnostico());
                    return consultaRepository.save(existingConsulta);
                });
    }

    @Override
    public Optional<Consulta> deleteConsulta(int id) {
        return consultaRepository.findById(id)
                .map(consulta -> {
                    consultaRepository.delete(consulta);
                    return consulta;
                });
    }

    @Override
    public Optional<Consulta> findByidCita(int idCita) {
        return consultaRepository.findConsultaByIdCita(idCita);
    }

    @Override
    public Optional<Consulta> findByMotivoConsulta(String motivoConsulta) {
        return consultaRepository.findByMotivoConsulta(motivoConsulta);
    }

}
