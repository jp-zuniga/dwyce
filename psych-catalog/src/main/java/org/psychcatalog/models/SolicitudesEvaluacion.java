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
@Table(name = "solicitudes_evaluacion")
public class SolicitudesEvaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instrumento_id", nullable = false)
    private Instrumento instrumento;

    @Size(max = 30)
    @ColumnDefault("'PENDIENTE'")
    @Column(name = "estado", length = 30)
    private String estado;

    @ColumnDefault("0")
    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_solicitud")
    private Instant fechaSolicitud;

    @OneToMany(mappedBy = "solicitud")
    private Set<Aplicacion> aplicaciones = new LinkedHashSet<>();
}
