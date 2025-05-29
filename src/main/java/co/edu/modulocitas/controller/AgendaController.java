package co.edu.modulocitas.controller;

import co.edu.modulocitas.enums.Estado;
import co.edu.modulocitas.model.Cita;
import co.edu.modulocitas.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @GetMapping("/cita/{idCita}")
    public Optional<Cita> consultarCita(@PathVariable Integer idCita) {
        return agendaService.consultarCitaPorId(idCita);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Cita crearCita(@RequestBody Cita cita) {
        return agendaService.crearCita(cita);
    }

    @PutMapping("/actualizar/{idCita}")
    public Optional<Cita> actualizarCita(@PathVariable int idCita, @RequestBody Cita cita) {
        return agendaService.actualizarCita(idCita, cita);
    }

    @PutMapping("/estado/{idCita}")
    public Optional<Cita> cambiarEstado(@PathVariable int idCita, @RequestParam Estado estado) {
        return agendaService.cambiarEstado(idCita, estado);
    }
}

