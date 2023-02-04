package org.cpl_cursos.java.Excursiones.servicios;

import org.cpl_cursos.java.Excursiones.modelos.Usuario;
import org.cpl_cursos.java.Excursiones.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSrvcImpl extends SrvcAbstracto<Usuario, Long, UsuarioRepository> {

    protected UsuarioSrvcImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }
}
