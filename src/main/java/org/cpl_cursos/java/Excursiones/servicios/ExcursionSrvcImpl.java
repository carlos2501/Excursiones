package org.cpl_cursos.java.Excursiones.servicios;

import org.cpl_cursos.java.Excursiones.modelos.Excursion;
import org.cpl_cursos.java.Excursiones.modelos.Reserva;
import org.cpl_cursos.java.Excursiones.modelos.Usuario;
import org.cpl_cursos.java.Excursiones.repositorios.ExcursionRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ExcursionSrvcImpl extends SrvcAbstracto<Excursion, Long, ExcursionRepository> {

    protected ExcursionSrvcImpl(ExcursionRepository excursionRepository) {
        super(excursionRepository);
    }

}
