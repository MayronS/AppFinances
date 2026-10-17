package com.appfinances.backend.dto;

import lombok.Data;

@Data
public class LancamentoResponse {

    private Integer id;
    private Integer usuarioId;
    private Integer contaId;
    private String tipo;
    private String descricao;
    private Double valor;
    private String categoria;
    private String data;
    private String criadoEm;

    public LancamentoResponse(com.appfinances.backend.model.Lancamento l) {
        this.id = l.getId();
        this.usuarioId = l.getUsuarioId();
        this.contaId = l.getContaId();
        this.tipo = l.getTipo();
        this.descricao = l.getDescricao();
        this.valor = l.getValor() != null ? l.getValor().doubleValue() : 0d;
        this.categoria = l.getCategoria();
        this.data = l.getData();
        this.criadoEm = l.getCriadoEm() != null ? l.getCriadoEm().toString() : null;
    }
}
