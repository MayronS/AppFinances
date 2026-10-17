package com.appfinances.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lancamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "conta_id", nullable = false)
    private Integer contaId;

    @Column(nullable = false)
    private String tipo; // ENTRADA ou SAIDA

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    private String categoria;

    @Column(nullable = false)
    private String data; // yyyy-MM-dd

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
}
