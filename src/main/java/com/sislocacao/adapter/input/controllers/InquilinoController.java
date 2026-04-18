package com.sislocacao.adapter.input.controllers;

import com.sislocacao.adapter.input.dto.request.SalvarInquilinoRequest;
import com.sislocacao.adapter.input.dto.response.InquilinoResponse;
import com.sislocacao.adapter.input.mapper.InquilinoMapper;
import com.sislocacao.core.domain.model.Inquilino;
import com.sislocacao.core.usecase.inquilino.command.SalvarInquilinoCommand;
import com.sislocacao.ports.input.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inquilinos")
public class InquilinoController {

    private final InquilinoMapper inquilinoMapper;
    private final SalvarInquilinoInputPort salvarInquilinoInputPort;
    private final BuscarInquilinosInputPort buscarInquilinosInputPort;
    private final BuscarInquilinoPorIdInputPort buscarInquilinoPorIdInputPort;
    private final AtualizarInquilinoInputPort atualizarInquilinoInputPort;
    private final ExcluirInquilinoInputPort excluirInquilinoInputPort;

    public InquilinoController(InquilinoMapper inquilinoMapper,
                               SalvarInquilinoInputPort salvarInquilinoInputPort,
                               BuscarInquilinosInputPort buscarInquilinosInputPort,
                               BuscarInquilinoPorIdInputPort buscarInquilinoPorIdInputPort,
                               AtualizarInquilinoInputPort atualizarInquilinoInputPort,
                               ExcluirInquilinoInputPort excluirInquilinoInputPort) {
        this.inquilinoMapper = inquilinoMapper;
        this.salvarInquilinoInputPort = salvarInquilinoInputPort;
        this.buscarInquilinosInputPort = buscarInquilinosInputPort;
        this.buscarInquilinoPorIdInputPort = buscarInquilinoPorIdInputPort;
        this.atualizarInquilinoInputPort = atualizarInquilinoInputPort;
        this.excluirInquilinoInputPort = excluirInquilinoInputPort;
    }

    @PostMapping
    public ResponseEntity<InquilinoResponse> criarInquilino(@RequestBody SalvarInquilinoRequest request){
        SalvarInquilinoCommand command = inquilinoMapper.paraSalvarInquilinoCommand(request);
        Inquilino inquilino = salvarInquilinoInputPort.executar(command);
        InquilinoResponse response = inquilinoMapper.paraInquilinoResponse(inquilino);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<InquilinoResponse>> listarInquilinos(){
        List<Inquilino> inquilinos = buscarInquilinosInputPort.execute();
        List<InquilinoResponse> responses = inquilinoMapper.paraInquilinosResponse(inquilinos);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InquilinoResponse> buscarPorId(@PathVariable Long id){
        Inquilino inquilino = buscarInquilinoPorIdInputPort.executar(id);
        InquilinoResponse response = inquilinoMapper.paraInquilinoResponse(inquilino);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InquilinoResponse> atualizarInquilino(@RequestBody SalvarInquilinoRequest request, @PathVariable Long id){
        SalvarInquilinoCommand command = inquilinoMapper.paraSalvarInquilinoCommand(request);
        Inquilino inquilino = atualizarInquilinoInputPort.executar(command, id);
        InquilinoResponse response = inquilinoMapper.paraInquilinoResponse(inquilino);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirInquilino(@PathVariable Long id){
        excluirInquilinoInputPort.executar(id);
        return ResponseEntity.noContent().build();
    }
}
