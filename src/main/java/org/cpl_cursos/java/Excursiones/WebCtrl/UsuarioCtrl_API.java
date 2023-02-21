package org.cpl_cursos.java.Excursiones.WebCtrl;

import org.cpl_cursos.java.Excursiones.modelos.Usuario;
import org.cpl_cursos.java.Excursiones.servicios.UsuarioSrvcImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioCtrl_API {
    // la variable srvc es una instancia de UsuarioSrvcImpl, que a su vez extiende SrvcAbstracto, por lo que puede
    // se puede acceder a todos sus métodos
    private final UsuarioSrvcImpl srvc;

    // ========= Contructor =============
    public UsuarioCtrl_API(UsuarioSrvcImpl srvc) {
        this.srvc = srvc;
    }
    /*
      ============ Métodos para la gestión de las peticiones HTTP ===================

        Cada uno indica la ruta que "atiende" y el verbo de la misma (GET, POST, etc.)
    */
    @GetMapping({"","/"})  // es la raiz de la ruta indicada en @RequestMapping
    public ResponseEntity<Set<Usuario>> listaUsuarios() {
        Set<Usuario> listaUsu = this.srvc.listarTodos();
        if(listaUsu.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaUsu, HttpStatus.OK);
    }
    // ----------------- Perfil de un usuario -------------------------
    @GetMapping("/{id}")
    public Optional<Usuario> buscaUsuarioPorId(@PathVariable Long id) {
        return this.srvc.buscarPorId(id);
    }
    // ----------------- Borrar un usuario por su Id-------------------------
    @DeleteMapping(value="/{id}")
    @ResponseBody
    public String borrarUsuario(@PathVariable Long id) {
        this.srvc.eliminarPorId(id);
        return "Eliminado el usuario " + id;
    }
    // ----------------- Borrar un usuario -------------------------
    @DeleteMapping(value="/borrar")
    @ResponseBody
    public String borrarUsu(@RequestBody Usuario usu) {
        this.srvc.eliminar(usu);
        return "Eliminado el usuario " + usu.getId();
    }
    // ----------------- Borrar usuarios en bloque -------------------------
    @DeleteMapping(value="/borravarios")
    @ResponseBody
    public String borrarUsuarios(@RequestBody Set<Usuario> usus) {
        this.srvc.eliminarSet(usus);
        return "Se han eliminado " + usus.size() + " usuarios";
    }
    // ----------------- Guardar un usuario -------------------------
    @PostMapping(value="/guardar")
    @ResponseBody
    public Usuario guardaUsuario(@RequestBody Usuario usu) {
        // los datos llegan en formato JSON
        this.srvc.guardar(usu);
        return usu;
    }
    // ----------------- Guardar usuarios en bloque -------------------------
    @PostMapping(value="/guardavarios")
    @ResponseBody
    public String guardaUsuarios(@RequestBody Set<Usuario> usus) {
        // los datos llegan en formato JSON
        this.srvc.guardarSet(usus);
        return ("Se han guardado " +  usus.size() + " usuarios.");
    }
}
