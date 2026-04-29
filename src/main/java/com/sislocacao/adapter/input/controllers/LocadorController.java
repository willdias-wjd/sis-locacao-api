package com.sislocacao.adapter.input.controllers;

import com.sislocacao.adapter.input.dto.request.SalvarLocadorRequest;
import com.sislocacao.adapter.input.dto.response.LocadorResponse;
import com.sislocacao.adapter.input.mapper.LocadorMapper;
import com.sislocacao.core.domain.model.Locador;
import com.sislocacao.core.usecase.locador.command.SalvarLocadorCommand;
import com.sislocacao.ports.input.AtualizarLocadorInputPort;
import com.sislocacao.ports.input.BuscarLocadoresInputPort;
import com.sislocacao.ports.input.BuscarLocadorPorIdInputPort;
import com.sislocacao.ports.input.ExcluirLocadorInputPort;
import com.sislocacao.ports.input.SalvarLocadorInputPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locadores")
public class LocadorController {

    private final LocadorMapper locadorMapper;
    private final SalvarLocadorInputPort salvarLocadorInputPort;
    private final BuscarLocadoresInputPort buscarLocadoresInputPort;
    private final BuscarLocadorPorIdInputPort buscarLocadorPorIdInputPort;
    private final AtualizarLocadorInputPort atualizarLocadorInputPort;
    private final ExcluirLocadorInputPort excluirLocadorInputPort;

    public LocadorController(LocadorMapper locadorMapper,
                             SalvarLocadorInputPort salvarLocadorInputPort,
                             BuscarLocadoresInputPort buscarLocadoresInputPort,
                             BuscarLocadorPorIdInputPort buscarLocadorPorIdInputPort,
                             AtualizarLocadorInputPort atualizarLocadorInputPort,
                             ExcluirLocadorInputPort excluirLocadorInputPort) {
        this.locadorMapper = locadorMapper;
        this.salvarLocadorInputPort = salvarLocadorInputPort;
        this.buscarLocadoresInputPort = buscarLocadoresInputPort;
        this.buscarLocadorPorIdInputPort = buscarLocadorPorIdInputPort;
        this.atualizarLocadorInputPort = atualizarLocadorInputPort;
        this.excluirLocadorInputPort = excluirLocadorInputPort;
    }

    @PostMapping
    public ResponseEntity<LocadorResponse> criarLocador(@Valid @RequestBody SalvarLocadorRequest request) {
        SalvarLocadorCommand command = locadorMapper.paraSalvarLocadorCommand(request);
        Locador locador = salvarLocadorInputPort.executar(command);
        return ResponseEntity.ok(locadorMapper.paraLocadorResponse(locador));
    }

    @GetMapping
    public ResponseEntity<List<LocadorResponse>> listarLocadores() {
        List<Locador> locadores = buscarLocadoresInputPort.execute();
        return ResponseEntity.ok(locadorMapper.paraLocadoresResponse(locadores));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocadorResponse> buscarLocadorPorId(@PathVariable Long id) {
        Locador locador = buscarLocadorPorIdInputPort.execute(id);
        return ResponseEntity.ok(locadorMapper.paraLocadorResponse(locador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocadorResponse> atualizarLocador(@PathVariable Long id,
                                                             @Valid @RequestBody SalvarLocadorRequest request) {
        SalvarLocadorCommand command = locadorMapper.paraSalvarLocadorCommand(request);
        Locador locador = atualizarLocadorInputPort.executar(command, id);
        return ResponseEntity.ok(locadorMapper.paraLocadorResponse(locador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLocador(@PathVariable Long id) {
        excluirLocadorInputPort.executar(id);
        return ResponseEntity.noContent().build();
    }
}
