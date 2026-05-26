package org.psychcatalog.repositories;

import java.util.List;

import org.psychcatalog.models.Categoria;
import org.psychcatalog.models.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;

@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {
    List<Instrumento> findByNombreContainingIgnoreCase(String nombre);
    List<Instrumento> findByCategoriaAndActivoTrue(@NotNull Categoria categoria);
    List<Instrumento> findByActivoTrueOrderByNombreAsc();
    boolean existsByNombreIgnoreCaseAndCategoriaId(String nombre, Long categoriaId);

    // join fetch para evitar n+1
    @Query(
        "SELECT i from Instrumento i " +
        "JOIN FETCH i.categoria " +
        "WHERE i.activo = true " +
        "AND lower(i.nombre) " +
        "like LOWER(CONCAT('%', :term, '%')) " +
        "ORDER BY i.nombre ASC"
    )
    List<Instrumento> buscarActivosPorNombre(@Param("term") String term);
}
