package co.edu.modulocitas.controller;


import co.edu.modulocitas.model.Consulta;
import co.edu.modulocitas.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("registroConsulta")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping
    public List<Consulta> findAll() { return consultaService.getConsultas();}

    @GetMapping("/cita")
    public ResponseEntity<Consulta> findByCita(@RequestParam Integer cita) {
        return consultaService.findByidCita(cita)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> findById(@RequestParam Integer idConsulta) {
        return consultaService.getConsulta(idConsulta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/motivo")
    public ResponseEntity<Consulta> findByMotivo(@RequestParam String motivoConsulta) {
        return consultaService.findByMotivoConsulta(motivoConsulta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Consulta save(@RequestBody Consulta consulta) {
        return consultaService.saveConsulta(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> update(@RequestBody Consulta consulta) {
        return consultaService.updateConsulta(consulta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Consulta> delete(@RequestParam Integer id) {
        return consultaService.deleteConsulta(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
