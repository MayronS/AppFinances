package com.appfinances.backend.repository;

import com.appfinances.backend.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {

    @Query("SELECT c FROM ContaBancaria c WHERE c.usuarioId = :usuarioId")
    List<ContaBancaria> findByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
