package org.cpl_cursos.java.Excursiones.repositorios;

import org.cpl_cursos.java.Excursiones.modelos.Contenido;
import org.cpl_cursos.java.Excursiones.modelos.Contenido_PK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContenidoRepository extends JpaRepository<Contenido, Contenido_PK> {
}