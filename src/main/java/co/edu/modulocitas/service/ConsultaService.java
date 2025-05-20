package co.edu.modulocitas.service;

import co.edu.modulocitas.model.Consulta;

import java.util.List;
import java.util.Optional;

public interface ConsultaService {
    List<Consulta> getConsultas();
    Optional<Consulta> getConsulta(int id);
    Consulta saveConsulta(Consulta consulta);
    Optional<Consulta> updateConsulta(Consulta consulta);
    Optional<Consulta> deleteConsulta(int id);
    Optional<Consulta> findByidCita(int idCita);
    Optional<Consulta> findByMotivoConsulta(String motivoConsulta);

}
