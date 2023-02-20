package org.cpl_cursos.java.Excursiones.WebCtrl;

import org.cpl_cursos.java.Excursiones.modelos.Usuario;
import org.cpl_cursos.java.Excursiones.servicios.UsuarioSrvcImpl;
import org.hibernate.annotations.Parameter;
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
    @GetMapping("/{id}")
    public Optional<Usuario> buscaUsuarioPorId(@PathVariable Long id) {
        return this.srvc.buscarPorId(id);
    }

    @PostMapping({"","/"})
    public void guardarUsuario(){
    }
}
