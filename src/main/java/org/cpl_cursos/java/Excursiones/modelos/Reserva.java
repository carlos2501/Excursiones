package org.cpl_cursos.java.Excursiones.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Reservas")
public class Reserva implements Serializable {
    @EmbeddedId
    private Reserva_PK reserva_PK;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name="idUsuario")
    private Usuario usu;    // contiene el objeto Usuario que ha realizado la reserva

    @ManyToOne
    @MapsId("idExcursion")
    @JoinColumn(name="idExcursion")
    private Excursion excur;  //// contiene el objeto Excursion que se ha reservado

    private Double precio;
    private LocalDate fecha;
    private Boolean pagada = false;

}