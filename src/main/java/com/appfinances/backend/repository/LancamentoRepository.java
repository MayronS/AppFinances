package com.appfinances.backend.repository;

import com.appfinances.backend.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

    @Query("""
        SELECT l FROM Lancamento l
        WHERE l.usuarioId = :usuarioId
        AND (:mesAno IS NULL OR :mesAno = '' OR l.data LIKE :mesAno || '%')
        AND (:busca IS NULL OR :busca = '' OR LOWER(l.descricao) LIKE LOWER('%' || :busca || '%'))
        ORDER BY l.data DESC, l.criadoEm DESC
        """)
    List<Lancamento> findByUsuarioAndFiltros(
            @Param("usuarioId") Integer usuarioId,
            @Param("mesAno") String mesAno,
            @Param("busca") String busca);

    void deleteById(Integer id);
}
