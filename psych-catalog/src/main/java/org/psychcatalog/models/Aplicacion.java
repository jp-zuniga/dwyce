package org.psychcatalog.models;

import org.hibernate.annotations.ColumnDefault;

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
@Table(name = "aplicaciones")
public class Aplicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitud_id")
    private SolicitudesEvaluacion solicitud;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instrumento_id", nullable = false)
    private Instrumento instrumento;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    @Column(name = "fecha_fin")
    private Instant fechaFin;

    @ColumnDefault("0")
    @Column(name = "puntaje_total")
    private Integer puntajeTotal;

    @Size(max = 100)
    @Column(name = "interpretacion", length = 100)
    private String interpretacion;

    @Size(max = 30)
    @ColumnDefault("'EN_PROCESO'")
    @Column(name = "estado", length = 30)
    private String estado;

    @OneToMany(mappedBy = "aplicacion")
    private Set<Respuesta> respuestas = new LinkedHashSet<>();
}
