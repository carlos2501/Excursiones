package org.cpl_cursos.java.Excursiones.WebCtrl;

import org.cpl_cursos.java.Excursiones.modelos.Excursion;
import org.cpl_cursos.java.Excursiones.modelos.Reserva;
import org.cpl_cursos.java.Excursiones.modelos.Usuario;
import org.cpl_cursos.java.Excursiones.servicios.ExcursionSrvcImpl;
import org.cpl_cursos.java.Excursiones.servicios.UsuarioSrvcImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

@RestController
@RequestMapping("/api/excursiones")
public class ExcursionCtrl_API {
    // la variable srvc es una instancia de UsuarioSrvcImpl, que a su vez extiende SrvcAbstracto, por lo que puede
    // se puede acceder a todos sus métodos
    private final ExcursionSrvcImpl srvc;

    // ========= Contructor =============
    public ExcursionCtrl_API(ExcursionSrvcImpl srvc) {
        this.srvc = srvc;
    }
    /*
          ============ Métodos para la gestión de las peticiones HTTP ===================

            Cada uno indica la ruta que "atiende" y el verbo de la misma (GET, POST, etc.)
        */
    @GetMapping("")  // es la raiz de la ruta indicada en @RequestMapping
    public ResponseEntity<Set<Excursion>> listaExcursiones() {
        Set<Excursion> listaExcur = this.srvc.listarTodos();
        if(listaExcur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaExcur, HttpStatus.OK);
    }

    @PostMapping(value="crear", consumes="application/json", produces="application/json")
    public ResponseEntity<Set<Excursion>> creaExcursiones(@RequestBody Set<Excursion> excur) {
        System.out.println("Han lledago: " + excur.size());
        try {
            this.srvc.guardarSet(excur);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
