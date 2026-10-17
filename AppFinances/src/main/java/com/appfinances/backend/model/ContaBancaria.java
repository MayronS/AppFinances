package com.appfinances.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Entity
@Table(name = "contas_bancarias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(nullable = false)
    private String nome;

    private String tipo;

    @Column(nullable = false)
    private BigDecimal saldo;
}
