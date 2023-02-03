package org.cpl_cursos.java.Excursiones.repositorios;

import org.cpl_cursos.java.Excursiones.modelos.Reserva;
import org.cpl_cursos.java.Excursiones.modelos.Reserva_PK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Reserva_PK> {
}