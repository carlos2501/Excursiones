package org.cpl_cursos.java.Excursiones.modelos;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "org/cpl_cursos/java/Excursiones")
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nombre;
    private String apodo;
    private Byte duracion;
    private Byte maxPersonas;
    private Byte dificultad;
    private Double puntosMedia;
    private Integer nroPuntuaciones;
    private Double precio;
    private Double descuento;
    private String resumen;
    private String descripcion;
    private String imgPortada;
    @NonNull
    @Column(name = "creado_el")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date creatoEl;

    @OneToMany(mappedBy = "excur")
    private Set<Reserva> reservas;      // contiene todos los objetos Reserva que se ha hecho de la excursión

    @OneToMany(mappedBy = "excurOpina")
    private Set<Contenido> contenidos;  // contiene todos los objetos Contenido de la excursión


}