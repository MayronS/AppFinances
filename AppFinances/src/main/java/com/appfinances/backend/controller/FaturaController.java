package com.appfinances.backend.controller;

import com.appfinances.backend.dto.EditarValorTotalFaturaRequest;
import com.appfinances.backend.dto.FaturaRequest;
import com.appfinances.backend.dto.FaturaResponse;
import com.appfinances.backend.dto.PagarFaturaRequest;
import com.appfinances.backend.model.Fatura;
import com.appfinances.backend.repository.FaturaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/faturas")
@CrossOrigin(origins = "*")
public class FaturaController {

    private final FaturaRepository faturaRepository;

    public FaturaController(FaturaRepository faturaRepository) {
        this.faturaRepository = faturaRepository;
    }

    @GetMapping
    public ResponseEntity<List<FaturaResponse>> listar(@RequestParam Integer usuarioId) {
        List<Fatura> lista = faturaRepository.findByUsuarioId(usuarioId);
        List<FaturaResponse> response = lista.stream()
                .map(FaturaResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<FaturaResponse> inserir(@Valid @RequestBody FaturaRequest request) {
        Fatura fatura = Fatura.builder()
                .usuarioId(request.getUsuarioId())
                .contaId(request.getContaId())
                .descricao(request.getDescricao())
                .bandeira(request.getBandeira())
                .valorTotal(request.getValorTotal())
                .valorPago(BigDecimal.ZERO)
                .dataVencimento(request.getDataVencimento())
                .dataAbertura(request.getDataAbertura())
                .status("PENDENTE")
                .build();

        fatura = faturaRepository.save(fatura);
        return ResponseEntity.ok(new FaturaResponse(fatura));
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<FaturaResponse> pagar(
            @PathVariable Integer id,
            @Valid @RequestBody PagarFaturaRequest request) {

        Fatura fatura = faturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fatura não encontrada"));

        BigDecimal novoPago = fatura.getValorPago() != null
                ? fatura.getValorPago().add(request.getValorPago())
                : request.getValorPago();

        fatura.setValorPago(novoPago);

        if (novoPago.compareTo(fatura.getValorTotal()) >= 0) {
            fatura.setStatus("PAGA");
        } else {
            fatura.setStatus("PENDENTE");
        }

        fatura = faturaRepository.save(fatura);
        return ResponseEntity.ok(new FaturaResponse(fatura));
    }

    @PutMapping("/{id}/valor-total")
    public ResponseEntity<FaturaResponse> editarValorTotal(
            @PathVariable Integer id,
            @Valid @RequestBody EditarValorTotalFaturaRequest request) {

        Fatura fatura = faturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fatura não encontrada"));

        fatura.setValorTotal(request.getValorTotal());

        // Recalcula status com base no valor pago atual
        BigDecimal pago = fatura.getValorPago() != null ? fatura.getValorPago() : BigDecimal.ZERO;
        if (pago.compareTo(fatura.getValorTotal()) >= 0) {
            fatura.setStatus("PAGA");
        } else {
            fatura.setStatus("PENDENTE");
        }

        fatura = faturaRepository.save(fatura);
        return ResponseEntity.ok(new FaturaResponse(fatura));
    }
}
