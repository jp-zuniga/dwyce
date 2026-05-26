package org.psychcatalog.models;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name = "preguntas")
public class Pregunta {
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
    @Column(name = "texto", nullable = false, length = Integer.MAX_VALUE)
    private String texto;

    @Size(max = 30)
    @NotNull
    @Column(name = "tipo", nullable = false, length = 30)
    private String tipo;

    @NotNull
    @Column(name = "orden", nullable = false)
    private Integer orden;

    @ColumnDefault("true")
    @Column(name = "obligatoria")
    private Boolean obligatoria;

    @OneToMany(mappedBy = "pregunta")
    private Set<OpcionesRespuesta> opcionesRespuestas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "pregunta")
    private Set<Respuesta> respuestas = new LinkedHashSet<>();
}
