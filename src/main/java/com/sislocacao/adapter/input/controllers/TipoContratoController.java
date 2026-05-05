package com.sislocacao.adapter.input.controllers;

import com.sislocacao.adapter.input.dto.request.SalvarTipoContratoRequest;
import com.sislocacao.adapter.input.dto.response.TipoContratoResponse;
import com.sislocacao.adapter.input.mapper.TipoContratoMapper;
import com.sislocacao.core.domain.model.TipoContrato;
import com.sislocacao.core.usecase.TipoContrato.command.SalvarTipoContratoCommand;
import com.sislocacao.ports.input.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-contrato")
public class TipoContratoController {

    private final SalvarTipoContratoInputPort salvarTipoContratoInputPort;
    private final BuscarTiposContratoInputPort buscarTiposContratoInputPort;
    private final BuscarTipoContratoPorIdInputPort buscarTipoContratoPorIdInputPort;
    private final AtualizarTipoContratoInputPort atualizarTipoContratoInputPort;
    private final ExcluirTipoContratoInputPort excluirTipoContratoInputPort;
    private final TipoContratoMapper tipoContratoMapper;

    public TipoContratoController(SalvarTipoContratoInputPort salvarTipoContratoInputPort,
                                  BuscarTiposContratoInputPort buscarTiposContratoInputPort,
                                  BuscarTipoContratoPorIdInputPort buscarTipoContratoPorIdInputPort,
                                  AtualizarTipoContratoInputPort atualizarTipoContratoInputPort,
                                  ExcluirTipoContratoInputPort excluirTipoContratoInputPort,
                                  TipoContratoMapper tipoContratoMapper) {
        this.salvarTipoContratoInputPort = salvarTipoContratoInputPort;
        this.buscarTiposContratoInputPort = buscarTiposContratoInputPort;
        this.buscarTipoContratoPorIdInputPort = buscarTipoContratoPorIdInputPort;
        this.atualizarTipoContratoInputPort = atualizarTipoContratoInputPort;
        this.excluirTipoContratoInputPort = excluirTipoContratoInputPort;
        this.tipoContratoMapper = tipoContratoMapper;
    }

    @PostMapping
    public ResponseEntity<TipoContratoResponse> criarTipoContrato(@Valid @RequestBody SalvarTipoContratoRequest request) {
        SalvarTipoContratoCommand command = tipoContratoMapper.paraSalvarTipoContratoCommand(request);
        TipoContrato tipoContrato = salvarTipoContratoInputPort.executar(command);
        return ResponseEntity.ok(tipoContratoMapper.paraTipoContratoResponse(tipoContrato));
    }

    @GetMapping
    public ResponseEntity<List<TipoContratoResponse>> listarTiposContrato() {
        List<TipoContrato> lista = buscarTiposContratoInputPort.executar();
        List<TipoContratoResponse> response = lista.stream()
                .map(tipoContratoMapper::paraTipoContratoResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoContratoResponse> buscarPorId(@PathVariable Long id) {
        TipoContrato tipoContrato = buscarTipoContratoPorIdInputPort.executar(id);
        return ResponseEntity.ok(tipoContratoMapper.paraTipoContratoResponse(tipoContrato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoContratoResponse> atualizar(@Valid @RequestBody SalvarTipoContratoRequest request,
                                                          @PathVariable Long id) {
        SalvarTipoContratoCommand command = tipoContratoMapper.paraSalvarTipoContratoCommand(request);
        TipoContrato tipoContrato = atualizarTipoContratoInputPort.executar(command, id);
        return ResponseEntity.ok(tipoContratoMapper.paraTipoContratoResponse(tipoContrato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirTipoContratoInputPort.executar(id);
        return ResponseEntity.noContent().build();
    }
}
