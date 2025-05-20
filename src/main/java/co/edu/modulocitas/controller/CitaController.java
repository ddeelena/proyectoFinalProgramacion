package co.edu.modulocitas.controller;


import co.edu.modulocitas.model.Cita;
import co.edu.modulocitas.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programarCita")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @GetMapping
    public List<Cita> findAll() { return citaService.getAllCitas(); }

    @GetMapping("/{motivoConsulta}")
     public ResponseEntity<Cita> findAllByMotivoConsulta(String motivoConsulta) {
        return citaService.getMotivoConsulta(motivoConsulta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> findById(Integer id) {
        return citaService.getCita(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cita save(@RequestBody Cita cita) {
        return citaService.saveCita(cita);
    }

    @PutMapping("/{id]")
    public ResponseEntity<Cita> updateCita(@PathVariable  Integer id , @RequestBody Cita cita) {
        return citaService.updateCita(id,cita)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Cita> deleteCita(@PathVariable Integer id) {
        return citaService.deleteCita(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
