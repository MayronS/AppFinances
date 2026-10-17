package com.appfinances.backend.controller;

import com.appfinances.backend.dto.LancamentoRequest;
import com.appfinances.backend.dto.LancamentoResponse;
import com.appfinances.backend.model.Lancamento;
import com.appfinances.backend.repository.LancamentoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lancamentos")
@CrossOrigin(origins = "*")
public class LancamentoController {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoController(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @GetMapping
    public ResponseEntity<List<LancamentoResponse>> listar(
            @RequestParam Integer usuarioId,
            @RequestParam(required = false) String mesAno,
            @RequestParam(required = false) String busca) {

        List<Lancamento> lista = lancamentoRepository.findByUsuarioAndFiltros(usuarioId, mesAno, busca);
        List<LancamentoResponse> response = lista.stream()
                .map(LancamentoResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<LancamentoResponse> inserir(@Valid @RequestBody LancamentoRequest request) {
        Lancamento lancamento = Lancamento.builder()
                .usuarioId(request.getUsuarioId())
                .contaId(request.getContaId())
                .tipo(request.getTipo())
                .descricao(request.getDescricao())
                .valor(request.getValor())
                .categoria(request.getCategoria())
                .data(request.getData())
                .criadoEm(LocalDateTime.now())
                .build();

        lancamento = lancamentoRepository.save(lancamento);
        return ResponseEntity.ok(new LancamentoResponse(lancamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        lancamentoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
