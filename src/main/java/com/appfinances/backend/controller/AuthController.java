package com.appfinances.backend.controller;

import com.appfinances.backend.dto.CadastroRequest;
import com.appfinances.backend.dto.LoginRequest;
import com.appfinances.backend.dto.LoginResponse;
import com.appfinances.backend.dto.UsuarioDto;
import com.appfinances.backend.model.Usuario;
import com.appfinances.backend.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(request.getEmail(), request.getSenha())
                .orElse(null);

        if (usuario == null) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "E-mail ou senha inválidos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
        }

        LoginResponse response = new LoginResponse(
                new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail()),
                "Login realizado com sucesso"
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastro(@Valid @RequestBody CadastroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "E-mail já cadastrado");
            return ResponseEntity.badRequest().body(erro);
        }

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(request.getSenha())
                .build();

        usuario = usuarioRepository.save(usuario);

        LoginResponse response = new LoginResponse(
                new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail()),
                "Conta criada com sucesso"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
