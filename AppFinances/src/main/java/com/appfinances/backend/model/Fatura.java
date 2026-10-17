package com.appfinances.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Entity
@Table(name = "faturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "conta_id", nullable = false)
    private Integer contaId;

    @Column(nullable = false)
    private String descricao;

    private String bandeira;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Column(name = "data_vencimento")
    private String dataVencimento; // yyyy-MM-dd

    @Column(name = "data_abertura")
    private String dataAbertura; // yyyy-MM-dd

    @Column(nullable = false)
    private String status; // PENDENTE, PAGA, VENCIDA
}
