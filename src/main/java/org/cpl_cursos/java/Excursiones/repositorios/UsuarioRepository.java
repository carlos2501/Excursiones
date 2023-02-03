package org.cpl_cursos.java.Excursiones.repositorios;

import org.cpl_cursos.java.Excursiones.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}