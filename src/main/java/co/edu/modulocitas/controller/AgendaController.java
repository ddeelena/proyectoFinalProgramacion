package co.edu.modulocitas.controller;

import co.edu.modulocitas.model.Cita;
import co.edu.modulocitas.service.AgendaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @RequestMapping("/cita")
    @GetMapping("/{id}")
    public Optional<Cita> consultarCita(Integer idCita) {
        return agendaService.consultarCita(idCita);
    }

    @PostMapping("/crear")
    public Cita crearCita(Cita cita) {
        return agendaService.crearCita(cita);
    }

    @RequestMapping("/actualizar")
    @PutMapping("/{id}")
    public Optional<Cita> actualizarCita(int id, Cita cita) {
        return agendaService.actualizarCita(id, cita);
    }


}
