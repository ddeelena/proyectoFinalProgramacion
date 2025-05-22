package co.edu.modulocitas.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idCita;
    private Integer idMascota;
    private String motivo;
    private String diagnostico;
    private String proceder;


}
