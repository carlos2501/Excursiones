package org.cpl_cursos.java.Excursiones.repositorios;

import org.cpl_cursos.java.Excursiones.modelos.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}