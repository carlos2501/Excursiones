package org.cpl_cursos.java.Excursiones.servicios;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

/*
    Esta clase sirve de generalización de la implementación común para todas las entidades.

    Aquí ponemos la implementación de los métodos más habituales y que todos, o la mayoría, de los
    modelos deben utilizar.

    Emplea los repositorios (o DAOs) de cada modelo.

    Posteriormente, debe existir para cada modelo una particularización de esta clase que podrá incorporar, además,
    sus propios métodos.

    <E> es el modelo -clase- para el que creamos el servicio
    <ID> es el tipo de dato que tiene el campo Id del modelo (PK)
    <REPO> es el repositorio del modelo.
 */
public abstract class SrvcAbstracto<E,ID, REPO extends JpaRepository> {

    // definimos las variables internas para utilizar a partir del constructor.
    private final REPO repo;
    // ============ Constructor ==========
    protected SrvcAbstracto(REPO repo) {
        this.repo = repo;
    }
    // ============ Getter ==============
    public REPO getRepo() {
        return repo;
    }

    /*
     ===============  Ahora añadimos los métodos a publicar  =================
    */
    public Set<E> listarTodos() {
        Set<E> eSet = new HashSet<E>(this.repo.findAll());
        return eSet;
        // Se puede poner en una sola línea -> return (Set<E>) new HashSet<E>(this.repo.findAll());
    }

    public Optional<E> buscarPorId(ID id) {
        return this.repo.findById(id);
    }

    public void guardar(E e) {
        this.repo.save(e);
    }

    public void guardarSet(Set<E> eSet ) {
        /*  Para recorrer un Set necesitamos un iterador que es una especie de cursor que permite ir obteniendo cada
            uno de los elementos del Set.
            Al inicializarse se coloca DELANTE del primero, de forma que la primera vez que se llama a su método next()
            obtenemos, si existe, el primer elemento.
         */
        Iterator<E> it = eSet.iterator();
        // Mientras haya elementos en el set ...
        while (it.hasNext()) {
            // ... lo grabamos
            this.repo.save(it.next());
        }
    }

    public void eliminar(E e) {
        this.repo.delete(e);
    }

    public void eliminarSet(Set<E> eSet){
        // hacemos lo mismo que en guardarSet
        Iterator<E> it =eSet.iterator();
        while (it.hasNext()) {
            eliminar(it.next());
            // también se puede llamar directamente al método del repositorio ->this.repo.delete(it.next());
        }
    }

    public void eliminarPorId(ID id) {
        this.repo.deleteById(id);
    }
}
