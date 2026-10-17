package com.appfinances.backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FaturaResponse {

    private Integer id;
    private Integer usuarioId;
    private Integer contaId;
    private String descricao;
    private String bandeira;
    private Double valorTotal;
    private Double valorPago;
    private String dataVencimento;
    private String dataAbertura;
    private String status;

    public FaturaResponse(com.appfinances.backend.model.Fatura f) {
        this.id = f.getId();
        this.usuarioId = f.getUsuarioId();
        this.contaId = f.getContaId();
        this.descricao = f.getDescricao();
        this.bandeira = f.getBandeira();
        this.valorTotal = f.getValorTotal() != null ? f.getValorTotal().doubleValue() : 0d;
        this.valorPago = f.getValorPago() != null ? f.getValorPago().doubleValue() : 0d;
        this.dataVencimento = f.getDataVencimento();
        this.dataAbertura = f.getDataAbertura();
        this.status = f.getStatus();
    }
}
