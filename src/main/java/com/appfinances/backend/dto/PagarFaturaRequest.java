package com.appfinances.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagarFaturaRequest {

    @NotNull
    @DecimalMin("0")
    private BigDecimal valorPago;
}
