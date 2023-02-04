package org.cpl_cursos.java.Excursiones.servicios;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cpl_cursos.java.Excursiones.modelos.Reserva;
import org.cpl_cursos.java.Excursiones.repositorios.ReservaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReservaSrvcImpl extends SrvcAbstracto<Reserva, Long, ReservaRepository> {

    protected ReservaSrvcImpl(ReservaRepository reservaRepository) {
        super(reservaRepository);
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
    public Reserva creaReserva(Reserva resv) {
        return resv;
    }

}
