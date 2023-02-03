package org.cpl_cursos.java.Excursiones.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Opiniones")
public class Contenido implements Serializable {
    @EmbeddedId
    private Contenido_PK contenido_PK;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name="idUsuario")
    private Usuario usuOpina;

    @ManyToOne
    @MapsId("idExcursion")
    @JoinColumn(name="idExcursion")
    private Excursion excurOpina;

    private String opinion;
    private Byte puntuacion;
    private Byte tipo; // Opinión , foto u otros datos futuros que sean compartidos por Usuario y Excursión
    private String urlFoto;
}