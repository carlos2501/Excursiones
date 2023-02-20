package org.cpl_cursos.java.Excursiones.repositorios;

import org.cpl_cursos.java.Excursiones.modelos.Contenido;
import org.cpl_cursos.java.Excursiones.modelos.Contenido_PK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContenidoRepository extends JpaRepository<Contenido, Contenido_PK> {
    List<Contenido> findAllByTipo(byte tipo);
}