package org.cpl_cursos.java.Excursiones.servicios;

import org.cpl_cursos.java.Excursiones.modelos.Reserva;
import org.cpl_cursos.java.Excursiones.modelos.Reserva_PK;
import org.cpl_cursos.java.Excursiones.repositorios.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservaSrvcImpl extends SrvcAbstracto<Reserva, Long, ReservaRepository> {

    private final ReservaRepository repo;

    protected ReservaSrvcImpl(ReservaRepository reservaRepository, ReservaRepository repo) {
        super(reservaRepository);
        this.repo = repo;
    }
/*
    public void importarDeJson(Reserva res, String json) throws IOException {
        // creamos una instancia de ObjectMapper para mapear los datos
        ObjectMapper objMap = new ObjectMapper();
        JsonFactory jsf = new JsonFactory();
        JsonParser jsp = jsf.createParser(json);
        // convertimos la cadena json a un objeto
        MappingIterator<Reserva> mi = objMap.readValues(jsp, Reserva.class);
        while(mi.hasNext()) {
            Reserva re = mi.next();
            System.out.println(re);
        }
    }
*/
    public Optional<Reserva> buscarPorId(Reserva_PK pk) {
        return this.repo.findById(pk);
    }

    public void eliminarPorId(Reserva_PK pk) {
        this.repo.deleteById(pk);
    }

    public Reserva creaReserva(Reserva resv) {
        return resv;
    }

}
