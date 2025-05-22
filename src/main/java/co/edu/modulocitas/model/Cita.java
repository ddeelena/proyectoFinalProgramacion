package co.edu.modulocitas.model;

import co.edu.modulocitas.enums.Estado;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idPaciente;
    private Integer idVeterinario;
    private LocalDate fecha;
    private Time hora;
    private boolean es_urgencia;
    private Estado estado;
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

}
