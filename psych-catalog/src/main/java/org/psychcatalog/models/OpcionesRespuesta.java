package org.psychcatalog.models;

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
@Table(name = "opciones_respuesta")
public class OpcionesRespuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;

    @Size(max = 200)
    @NotNull
    @Column(name = "texto", nullable = false, length = 200)
    private String texto;

    @NotNull
    @Column(name = "valor", nullable = false)
    private Integer valor;

    @NotNull
    @Column(name = "orden", nullable = false)
    private Integer orden;

    @OneToMany(mappedBy = "opcionRespuesta")
    private Set<Respuesta> respuestas = new LinkedHashSet<>();
}
