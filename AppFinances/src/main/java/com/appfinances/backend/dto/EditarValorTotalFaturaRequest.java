package com.appfinances.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EditarValorTotalFaturaRequest {

    @NotNull
    @DecimalMin("0")
    private BigDecimal valorTotal;
}
