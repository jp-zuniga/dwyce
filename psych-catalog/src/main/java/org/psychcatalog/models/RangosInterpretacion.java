package org.psychcatalog.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rangos_interpretacion")
public class RangosInterpretacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "instrumento_id", nullable = false)
    private Instrumento instrumento;

    @NotNull
    @Column(name = "puntaje_min", nullable = false)
    private Integer puntajeMin;

    @NotNull
    @Column(name = "puntaje_max", nullable = false)
    private Integer puntajeMax;

    @Size(max = 100)
    @NotNull
    @Column(name = "nivel", nullable = false, length = 100)
    private String nivel;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;
}
