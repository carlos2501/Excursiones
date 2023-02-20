package org.cpl_cursos.java.Excursiones.servicios;

import org.cpl_cursos.java.Excursiones.modelos.*;
import org.cpl_cursos.java.Excursiones.repositorios.ContenidoRepository;
import org.hibernate.usertype.BaseUserTypeSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContenidoSrvcImpl extends SrvcAbstracto<Contenido, Long, ContenidoRepository> {

    private final ContenidoRepository repo;

    protected ContenidoSrvcImpl(ContenidoRepository contRepo) {
        super(contRepo);
        this.repo = contRepo;
    }

    public Optional<Contenido> buscarPorId(Contenido_PK pk) {
        return this.repo.findById(pk);
    }

    public List<Contenido> buscarPorTipo(Byte tipo) {
        return  this.repo.findAllByTipo(tipo);
    }

    public void eliminarPorId(Contenido_PK pk) {
        this.repo.deleteById(pk);
    }

    public Contenido creaContenido(Contenido cont) {
        this.repo.save(cont);
        return cont;
    }
}
