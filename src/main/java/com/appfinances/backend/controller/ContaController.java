package com.appfinances.backend.controller;

import com.appfinances.backend.dto.ContaBancariaRequest;
import com.appfinances.backend.model.ContaBancaria;
import com.appfinances.backend.repository.ContaBancariaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contas")
@CrossOrigin(origins = "*")
public class ContaController {

    private final ContaBancariaRepository contaRepository;

    public ContaController(ContaBancariaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @GetMapping
    public ResponseEntity<List<ContaBancaria>> listar(@RequestParam Integer usuarioId) {
        List<ContaBancaria> lista = contaRepository.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ContaBancaria> inserir(@Valid @RequestBody ContaBancariaRequest request) {
        ContaBancaria conta = ContaBancaria.builder()
                .usuarioId(request.getUsuarioId())
                .nome(request.getNome())
                .tipo(request.getTipo())
                .saldo(request.getSaldo())
                .build();

        conta = contaRepository.save(conta);
        return ResponseEntity.ok(conta);
    }
}
