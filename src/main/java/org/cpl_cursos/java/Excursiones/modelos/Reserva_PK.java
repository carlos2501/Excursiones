package org.cpl_cursos.java.Excursiones.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Reserva_PK implements Serializable {
    @Column(name="id_Usuario", nullable = false)
    private Long idUsuario;

    @Column(name="id_Excursion", nullable = false)
    private Long idExcursion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva_PK that)) return false;
        return getIdUsuario().equals(that.getIdUsuario()) && getIdExcursion().equals(that.getIdExcursion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUsuario(), getIdExcursion());
    }
}