package org.cpl_cursos.java.Excursiones.WebCtrl;

import org.cpl_cursos.java.Excursiones.modelos.Reserva;
import org.cpl_cursos.java.Excursiones.modelos.Usuario;
import org.cpl_cursos.java.Excursiones.servicios.ReservaSrvcImpl;
import org.cpl_cursos.java.Excursiones.servicios.UsuarioSrvcImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/reservas")
public class ReservaCtrl_API {
    // la variable srvc es una instancia de UsuarioSrvcImpl, que a su vez extiende SrvcAbstracto, por lo que puede
    // se puede acceder a todos sus métodos
    private final ReservaSrvcImpl srvc;

    // ========= Contructor =============
    public ReservaCtrl_API(ReservaSrvcImpl srvc) {
        this.srvc = srvc;
    }
    /*
      ============ Métodos para la gestión de las peticiones HTTP ===================

        Cada uno indica la ruta que "atiende" y el verbo de la misma (GET, POST, etc.)
    */
    @GetMapping("")  // es la raiz de la ruta indicada en @RequestMapping
    public ResponseEntity<Set<Reserva>> listaReservas() {
        Set<Reserva> listaRes = this.srvc.listarTodos();
        if(listaRes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaRes, HttpStatus.OK);
    }

    @PostMapping(value="", consumes="application/json", produces="application/json")
    public Reserva creaReserva(@RequestBody Reserva rsva) {
        System.out.println(rsva);
        return this.srvc.creaReserva(rsva);
    }
}
