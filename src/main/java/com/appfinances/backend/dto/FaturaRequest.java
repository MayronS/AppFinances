package com.appfinances.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FaturaRequest {

    @NotNull
    private Integer usuarioId;

    @NotNull
    private Integer contaId;

    @NotBlank
    private String descricao;

    private String bandeira;

    @NotNull
    @DecimalMin("0")
    private BigDecimal valorTotal;

    private String dataVencimento; // yyyy-MM-dd

    private String dataAbertura; // yyyy-MM-dd
}
