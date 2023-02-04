package org.cpl_cursos.java.Excursiones.servicios;

import org.cpl_cursos.java.Excursiones.modelos.Excursion;
import org.cpl_cursos.java.Excursiones.modelos.Usuario;
import org.cpl_cursos.java.Excursiones.repositorios.ContenidoRepository;
import org.cpl_cursos.java.Excursiones.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ContenidpSrvcImpl extends SrvcAbstracto<Excursion, Long, ContenidoRepository> {

    protected ContenidpSrvcImpl(ContenidoRepository contenidoRepository) {
        super(contenidoRepository);
    }
}
