package org.cpl_cursos.java.Excursiones.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class Contenido_PK implements Serializable {
    @Column(name="id_Usuario", nullable = false)
    private Long idUsuario;

    @Column(name="id_Excursion", nullable = false)
    private Long idExcursion;

    @Column(name="fecha", nullable = false)
    private LocalDate fecha;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contenido_PK that)) return false;

        if (!getIdUsuario().equals(that.getIdUsuario())) return false;
        if (!getIdExcursion().equals(that.getIdExcursion())) return false;
        return getFecha().equals(that.getFecha());
    }

    @Override
    public int hashCode() {
        int result = getIdUsuario().hashCode();
        result = 31 * result + getIdExcursion().hashCode();
        result = 31 * result + getFecha().hashCode();
        return result;
    }
}