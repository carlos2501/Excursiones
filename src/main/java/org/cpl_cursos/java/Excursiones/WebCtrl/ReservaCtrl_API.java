package org.cpl_cursos.java.Excursiones.WebCtrl;

import org.cpl_cursos.java.Excursiones.modelos.Reserva;
import org.cpl_cursos.java.Excursiones.modelos.Reserva_PK;
import org.cpl_cursos.java.Excursiones.modelos.Usuario;
import org.cpl_cursos.java.Excursiones.servicios.ReservaSrvcImpl;
import org.cpl_cursos.java.Excursiones.servicios.UsuarioSrvcImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/reservas")
public class ReservaCtrl_API {
    // la variable srvc es una instancia de ReservaSrvcImpl, que a su vez extiende SrvcAbstracto, por lo que puede
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
    @GetMapping({"","/"})  // es la raiz de la ruta indicada en @RequestMapping
    public ResponseEntity<Set<Reserva>> listaReservas() {
        Set<Reserva> listaRes = this.srvc.listarTodos();
        if(listaRes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaRes, HttpStatus.OK);
    }
    // ----------------- Datos de la reserva -------------------------
    @GetMapping(value="/{idusu}/{idexc}", produces="application/json")
    public ResponseEntity<Reserva> leerReservaporId(@PathVariable  Map<Long, Long> pathVarMap) {
        Long usu = pathVarMap.get("idusu");
        Long idexc = pathVarMap.get("idexc");
        Reserva_PK pk = new Reserva_PK(usu, idexc);
        // No hace falta que el parámetro sea opcional. SI no se indica el parámetro, se ejecuta la ruta raíz
        Optional<Reserva> res = this.srvc.buscarPorId(pk);
        return ResponseEntity.of(res);
    }
    // ----------------- Borrar una reserva por su Id-------------------------
    @DeleteMapping(value="/{idusu}/{idexc}", produces="application/json")
    public ResponseEntity<String> borrarReserva(@PathVariable Map<Long, Long> pathVarMap) {
        Long usu = pathVarMap.get("idusu");
        Long idexc = pathVarMap.get("idexc");
        Reserva_PK pk = new Reserva_PK(usu, idexc);
        this.srvc.eliminarPorId(pk);
        return new ResponseEntity<>("Eliminada la reserva " + pk.toString(), HttpStatus.OK);
    }
    // ----------------- Borrar una reserva -------------------------
    @DeleteMapping(value="/borrar")
    @ResponseBody
    public String borrarRes(@RequestBody Reserva res) {
        this.srvc.eliminar(res);
        return "Eliminada la reserva " + res.getReserva_PK();
    }
    // ----------------- Borrar reservas en bloque -------------------------
    @DeleteMapping(value="/borravarios")
    @ResponseBody
    public String borrarReservas(@RequestBody Set<Reserva> reservas) {
        this.srvc.eliminarSet(reservas);
        return "Se han eliminado " + reservas.size() + " reservas.";
    }
    // ----------------- Guardar una reserva -------------------------
    @PostMapping(value="/guardar")
    @ResponseBody
    public Reserva guardaReserva(@RequestBody Reserva res) {
        // los datos llegan en formato JSON
        this.srvc.guardar(res);
        return res;
    }
    // ----------------- Guardar reservas en bloque -------------------------
    @PostMapping(value="/guardavarios")
    @ResponseBody
    public String guardaReservas(@RequestBody Set<Reserva> reservas) throws SQLException {
        // los datos llegan en formato JSON
        this.srvc.guardarSet(reservas);
        return ("Se han guardado " +  reservas.size() + " reservas.");
    }

    @PostMapping(value="", consumes="application/json", produces="application/json")
    // los atributos consumes y produces hacen que se manejen los datos en formato JSON
    public Reserva creaReserva(@RequestBody Reserva rsva) {
        System.out.println(rsva);
        return this.srvc.creaReserva(rsva);
    }
}
