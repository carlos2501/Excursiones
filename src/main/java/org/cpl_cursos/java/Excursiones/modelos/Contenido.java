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
@Table(name = "Contenidos")
public class Contenido implements Serializable {
    @EmbeddedId
    private Contenido_PK contenido_PK;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name="id_Usuario")
    private Usuario usu;

    @ManyToOne
    @MapsId("idExcursion")
    @JoinColumn(name="id_Excursion")
    private Excursion excur;

    private String opinion;
    private Byte puntuacion;
    private Byte tipo; // Opinión (1), foto (2) u otros datos futuros que sean compartidos por Usuario y Excursión
    private String urlFoto;
}