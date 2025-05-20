package co.edu.modulocitas.model;

import jakarta.persistence.*;
import lombok.Data;


@Table
@Data
@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipo;
    private String descripcion;
    private Integer duracion;
    private String requisitos;


}
