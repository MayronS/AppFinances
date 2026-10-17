package com.appfinances.backend.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private Integer id;
    private String nome;
    private String email;
    private String mensagem;

    public LoginResponse(UsuarioDto usuario, String mensagem) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.mensagem = mensagem;
    }
}
