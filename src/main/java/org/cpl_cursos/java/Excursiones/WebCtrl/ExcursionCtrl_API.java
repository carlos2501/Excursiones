package org.cpl_cursos.java.Excursiones.WebCtrl;

import org.cpl_cursos.java.Excursiones.modelos.Excursion;
import org.cpl_cursos.java.Excursiones.modelos.Reserva;
import org.cpl_cursos.java.Excursiones.modelos.Usuario;
import org.cpl_cursos.java.Excursiones.servicios.ExcursionSrvcImpl;
import org.cpl_cursos.java.Excursiones.servicios.UsuarioSrvcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/excursiones")
public class ExcursionCtrl_API {
    // la variable srvc es una instancia de UsuarioSrvcImpl, que a su vez extiende SrvcAbstracto, por lo que puede
    // se puede acceder a todos sus métodos
    private final ExcursionSrvcImpl srvc;

    // ========= Contructor =============
    @Autowired
    public ExcursionCtrl_API(ExcursionSrvcImpl srvc) {
        this.srvc = srvc;
    }
    /*
          ============ Métodos para la gestión de las peticiones HTTP ===================

            Cada uno indica la ruta que "atiende" y el verbo de la misma (GET, POST, etc.)
        */
    @GetMapping({"","/"})  // es la raiz de la ruta indicada en @RequestMapping
    public ResponseEntity<Set<Excursion>> listaExcursiones() {
        Set<Excursion> listaExcur = this.srvc.listarTodos();
        if(listaExcur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaExcur, HttpStatus.OK);
    }
    // ----------------- ficha de una excursión -------------------------
    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Excursion> leerExcursionporId(@PathVariable Long id) {
        // No hace falta que el parámetro sea opcional. SI no se indica el parámetro, se ejecuta la ruta raíz
        Optional<Excursion> excur = this.srvc.buscarPorId(id);
        return ResponseEntity.of(excur);
    }
    // ----------------- Borrar una excursion -------------------------
    @DeleteMapping(value="/borrar")
    @ResponseBody
    public String borrarExc(@RequestBody Excursion excur) {
        this.srvc.eliminar(excur);
        return "Eliminada la excursion " + excur.getId();
    }
    // ----------------- Borrar excursiones en bloque -------------------------
    @DeleteMapping(value="/borravarios")
    @ResponseBody
    public String borrarExcursiones(@RequestBody Set<Excursion> excurs) {
        this.srvc.eliminarSet(excurs);
        return "Se han eliminado " + excurs.size() + " excursiones.";
    }
    // ----------------- Guardar una excursión -------------------------
    @PostMapping(value="/guardar")
    @ResponseBody
    public Excursion guardaExcursion(@RequestBody Excursion exc) {
        // los datos llegan en formato JSON
        this.srvc.guardar(exc);
        return exc;
    }
    // ----------------- Guardar excursiones en bloque -------------------------
    @PostMapping(value="/guardavarios")
    @ResponseBody
    public String guardaExcrusiones(@RequestBody Set<Excursion> excurs) throws SQLException {
        // los datos llegan en formato JSON
        this.srvc.guardarSet(excurs);
        return ("Se han guardado " +  excurs.size() + " excursiones.");
    }
}
