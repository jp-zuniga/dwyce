package org.psychcatalog.models;

import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "instrumentos")
public class Instrumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Size(max = 150)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @NotNull
    @Column(name = "descripcion", nullable = false, length = Integer.MAX_VALUE)
    private String descripcion;

    @Column(name = "instrucciones", length = Integer.MAX_VALUE)
    private String instrucciones;

    @Size(max = 255)
    @Column(name = "imagen_url")
    private String imagenUrl;

    @ColumnDefault("0")
    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;

    @ColumnDefault("true")
    @Column(name = "activo")
    private Boolean activo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "instrumento")
    private Set<Aplicacion> aplicaciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "instrumento")
    private Set<Pregunta> preguntas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "instrumento")
    private Set<RangosInterpretacion> rangosInterpretacions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "instrumento")
    private Set<SolicitudesEvaluacion> solicitudesEvaluacions = new LinkedHashSet<>();
}
