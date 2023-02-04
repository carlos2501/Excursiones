package org.cpl_cursos.java.Excursiones.modelos;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NonNull
    private String emilio;
    private String nombre;
    @NonNull
    private String clave;
    private String rol;
    private String foto;
    private Boolean activo = false;
    @NonNull
    @Column(name = "creado_el")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date creadoEl;

    @OneToMany(mappedBy = "usu")
    private Set<Reserva> reservas;  //contiene todas las reservas realizadas por el usuario

    @OneToMany(mappedBy = "usuOpina")
    private Set<Contenido> contenidos;  // contiene todas las opiniones y fotos subidas por el usuario
}
