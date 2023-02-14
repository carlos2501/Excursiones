package org.cpl_cursos.java.Excursiones.WebCtrl;

import org.cpl_cursos.java.Excursiones.modelos.*;
import org.cpl_cursos.java.Excursiones.servicios.ContenidoSrvcImpl;
import org.cpl_cursos.java.Excursiones.utiles.SubeArchivoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/contenido")
public class ContenidoCtrl_API {
    // la variable srvc es una instancia de ReservaSrvcImpl, que a su vez extiende SrvcAbstracto, por lo que puede
    // se puede acceder a todos sus métodos
    @Autowired
    private ContenidoSrvcImpl srvc;

    // ========= Contructor =============
    //public ContenidoCtrl_API(ContenidoSrvcImpl srvc) {
    //    this.srvc = srvc;
    //}
    /*
          ============ Métodos para la gestión de las peticiones HTTP ===================

            Cada uno indica la ruta que "atiende" y el verbo de la misma (GET, POST, etc.)
        */
    @GetMapping({"","/"})  // es la raiz de la ruta indicada en @RequestMapping
    public ResponseEntity<Set<Contenido>> listaContenidos() {
        Set<Contenido> listaCon = this.srvc.listarTodos();
        if(listaCon.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaCon, HttpStatus.OK);
    }
    // ----------------- Datos del contenido -------------------------
    @GetMapping("")
    public ResponseEntity<Contenido> leerContenidoporId(@RequestParam  Map<String, String> pathVarMap) {
        Long usu = Long.valueOf(pathVarMap.get("idusu"));
        Long idexc = Long.valueOf(pathVarMap.get("idexc"));
        LocalDate fecha = LocalDate.parse(pathVarMap.get("fecha"));
        Contenido_PK pk = new Contenido_PK(usu, idexc, fecha);
        // No hace falta que el parámetro sea opcional. SI no se indica el parámetro, se ejecuta la ruta raíz
        Optional<Contenido> res = this.srvc.buscarPorId(pk);
        return ResponseEntity.of(res);
    }
    // ----------------- Borrar un contenido por su Id-------------------------
    @DeleteMapping(value="")
    public ResponseEntity<String> borrarContenidoporId(@RequestParam  Map<String, String> pathVarMap) {
        Long usu = Long.valueOf(pathVarMap.get("idusu"));
        Long idexc = Long.valueOf(pathVarMap.get("idexc"));
        LocalDate fecha = LocalDate.parse(pathVarMap.get("fecha"));
        Contenido_PK pk = new Contenido_PK(usu, idexc, fecha);
        this.srvc.eliminarPorId(pk);
        return new ResponseEntity<>("Eliminado el contenido " + pk, HttpStatus.OK);
    }
    // ----------------- Borrar un contenido -------------------------
    @DeleteMapping(value="/borrar")
    @ResponseBody
    public String borrarContenido(@RequestBody Contenido cont) {
        this.srvc.eliminar(cont);
        return "Eliminado el contenido " + cont.getContenido_PK();
    }
    // ----------------- Borrar contenidos en bloque -------------------------
    @DeleteMapping(value="/borravarios")
    @ResponseBody
    public String borrarContenidos(@RequestBody Set<Contenido> contenidos) {
        this.srvc.eliminarSet(contenidos);
        return "Se han eliminado " + contenidos.size() + " contenidos";
    }
    // ----------------- Guardar un contenido -------------------------
    @PostMapping(value="/guardar")
    @ResponseBody
    public String guardaContenido(@RequestParam Map<String,String> params,
                                  @RequestParam("foto") MultipartFile foto) throws IOException {
        Contenido cont = new Contenido();
        cont.setContenido_PK(new Contenido_PK(Long.valueOf(params.get("idUsuario")),
                Long.valueOf(params.get("idExcursion")),
                LocalDate.parse(params.get("fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        ));
        cont.setTipo(Byte.valueOf(params.get("tipo")));
        cont.setUrlFoto(StringUtils.cleanPath(foto.getOriginalFilename()));
        String dirSubida = "fotos/u" + Long.valueOf(params.get("idUsuario")) + "_e" + Long.valueOf(params.get("idExcursion"));
        String arch = StringUtils.cleanPath(foto.getOriginalFilename());
        SubeArchivoUtil.grabaArchivo(dirSubida, arch, foto);

        return params.entrySet().toString();
    }
    // ----------------- Guardar contenidos en bloque -------------------------
    @PostMapping(value="/guardavarios")
    @ResponseBody
    public String guardaContenidos(@RequestBody Set<Contenido> cont) throws SQLException {
        // los datos llegan en formato JSON
        this.srvc.guardarSet(cont);
        return ("Se han guardado " +  cont.size() + " contenidos.");
    }

    @PostMapping(value="", consumes={"application/json"})
    @ResponseBody
    // los atributos consumes y produces hacen que se manejen los datos en formato JSON
    public Contenido creaContenido(@RequestBody Contenido cont) {
        System.out.println(cont);
        return this.srvc.creaContenido(cont);
    }
}
