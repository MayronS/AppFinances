package com.appfinances.backend.repository;

import com.appfinances.backend.model.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Integer> {

    @Query("SELECT f FROM Fatura f WHERE f.usuarioId = :usuarioId ORDER BY f.dataVencimento")
    List<Fatura> findByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
