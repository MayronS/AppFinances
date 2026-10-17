package com.appfinances.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LancamentoRequest {

    @NotNull
    private Integer usuarioId;

    @NotNull
    private Integer contaId;

    @NotBlank
    private String tipo; // ENTRADA ou SAIDA

    @NotBlank
    private String descricao;

    @NotNull
    @DecimalMin("0")
    private BigDecimal valor;

    private String categoria;

    @NotBlank
    private String data; // yyyy-MM-dd
}
