package com.appfinances.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaBancariaRequest {

    @NotNull
    private Integer usuarioId;

    @NotBlank
    private String nome;

    private String tipo;

    @NotNull
    @DecimalMin("0")
    private BigDecimal saldo;
}
