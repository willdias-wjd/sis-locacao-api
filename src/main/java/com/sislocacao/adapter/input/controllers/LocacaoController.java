package com.sislocacao.adapter.input.controllers;

import com.sislocacao.adapter.input.dto.request.SalvarLocacaoRequest;
import com.sislocacao.adapter.input.dto.response.LocacaoResponse;
import com.sislocacao.adapter.input.mapper.LocacaoMapper;
import com.sislocacao.core.domain.model.Locacao;
import com.sislocacao.core.usecase.locacao.command.SalvarLocacaoCommand;
import com.sislocacao.ports.input.AtualizarLocacaoInputPort;
import com.sislocacao.ports.input.BuscarLocacoesInputPort;
import com.sislocacao.ports.input.ExcluirLocacaoInputPort;
import com.sislocacao.ports.input.SalvarLocacaoInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locacao")
public class LocacaoController {

    private final LocacaoMapper locacaoMapper;
    private final SalvarLocacaoInputPort salvarLocacaoInputPort;
    private final BuscarLocacoesInputPort buscarLocacoesInputPort;
    private final AtualizarLocacaoInputPort atualizarLocacaoInputPort;
    private final ExcluirLocacaoInputPort excluirLocacaoInputPort;

    public LocacaoController(LocacaoMapper locacaoMapper, SalvarLocacaoInputPort salvarLocacaoInputPort, BuscarLocacoesInputPort buscarLocacoesInputPort, AtualizarLocacaoInputPort atualizarLocacaoInputPort, ExcluirLocacaoInputPort excluirLocacaoInputPort) {
        this.locacaoMapper = locacaoMapper;
        this.salvarLocacaoInputPort = salvarLocacaoInputPort;
        this.buscarLocacoesInputPort = buscarLocacoesInputPort;
        this.atualizarLocacaoInputPort = atualizarLocacaoInputPort;
        this.excluirLocacaoInputPort = excluirLocacaoInputPort;
    }

    @PostMapping
    public ResponseEntity<LocacaoResponse> criarLocacao(@RequestBody SalvarLocacaoRequest request){
        SalvarLocacaoCommand command = locacaoMapper.paraSalvarLocacaoCommand(request);
        Locacao locacao = salvarLocacaoInputPort.executar(command);
        LocacaoResponse response = locacaoMapper.paraLocacaoResponse(locacao);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LocacaoResponse>> listarLocacoes(){
        List<Locacao> locacoes = buscarLocacoesInputPort.execute();
        List<LocacaoResponse> responses = locacaoMapper.paraLocacoesResponse(locacoes);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/locacaoId/{locacaoId}")
    public ResponseEntity<LocacaoResponse> atualizarLocacao(@RequestBody SalvarLocacaoRequest request, @PathVariable Long locacaoId){
        SalvarLocacaoCommand command = locacaoMapper.paraSalvarLocacaoCommand(request);
        Locacao locacao = atualizarLocacaoInputPort.executar(command, locacaoId);
        LocacaoResponse response = locacaoMapper.paraLocacaoResponse(locacao);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLocacao(@PathVariable Long id){
        excluirLocacaoInputPort.executar(id);
        return ResponseEntity.noContent().build();
    }
}
