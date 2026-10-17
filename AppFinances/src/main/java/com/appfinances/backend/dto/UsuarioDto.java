package com.appfinances.backend.dto;

import lombok.Data;

@Data
public class UsuarioDto {

    private Integer id;
    private String nome;
    private String email;

    public UsuarioDto(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
}
