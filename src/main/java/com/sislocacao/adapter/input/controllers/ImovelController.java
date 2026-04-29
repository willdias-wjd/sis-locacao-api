package com.sislocacao.adapter.input.controllers;

import com.sislocacao.adapter.input.dto.request.SalvarImovelRequest;
import com.sislocacao.adapter.input.dto.response.ImovelResponse;
import com.sislocacao.adapter.input.mapper.ImovelMapper;
import com.sislocacao.core.domain.model.Imovel;
import com.sislocacao.core.usecase.imovel.command.SalvarImovelCommand;
import com.sislocacao.ports.input.SalvarImovelInputPort;
import com.sislocacao.ports.input.BuscarImoveisInputPort;
import com.sislocacao.ports.input.BuscarImovelPorIdInputPort;
import com.sislocacao.ports.input.AtualizarImovelInputPort;
//import com.sislocacao.ports.input.ExcluirImovelInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imovel")
public class ImovelController {

    private final ImovelMapper imovelMapper;
    private final SalvarImovelInputPort salvarImovelInputPort;
    private final BuscarImoveisInputPort buscarImoveisInputPort;
    private final BuscarImovelPorIdInputPort buscarImovelPorIdInputPort;
    private final AtualizarImovelInputPort atualizarImovelInputPort;
//    private final ExcluirImovelInputPort excluirImovelInputPort;

    public ImovelController(ImovelMapper imovelMapper,
                            SalvarImovelInputPort salvarImovelInputPort,
                            BuscarImoveisInputPort buscarImoveisInputPort,
                            BuscarImovelPorIdInputPort buscarImovelPorIdInputPort,
                            AtualizarImovelInputPort atualizarImovelInputPort) {
        this.imovelMapper = imovelMapper;
        this.salvarImovelInputPort = salvarImovelInputPort;
        this.buscarImoveisInputPort = buscarImoveisInputPort;
        this.buscarImovelPorIdInputPort = buscarImovelPorIdInputPort;
        this.atualizarImovelInputPort = atualizarImovelInputPort;
//        this.excluirImovelInputPort = excluirImovelInputPort;
    }

    @PostMapping
    public ResponseEntity<ImovelResponse> criarImovel(@RequestBody SalvarImovelRequest request){
        SalvarImovelCommand command = imovelMapper.paraSalvarImovelCommand(request);
        Imovel imovel = salvarImovelInputPort.executar(command);
        ImovelResponse response = imovelMapper.paraImovelResponse(imovel);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ImovelResponse>> listarImoveis(){
        List<Imovel> imoveis = buscarImoveisInputPort.execute();
        List<ImovelResponse> responses = imovelMapper.paraImoveisResponse(imoveis);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImovelResponse> buscarPorId(@PathVariable Long id){
        Imovel imovel = buscarImovelPorIdInputPort.execute(id);
        ImovelResponse response = imovelMapper.paraImovelResponse(imovel);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImovelResponse> atualizarImovel(@RequestBody SalvarImovelRequest request, @PathVariable Long id){
        SalvarImovelCommand command = imovelMapper.paraSalvarImovelCommand(request);
        Imovel imovel = atualizarImovelInputPort.executar(command, id);
        ImovelResponse response = imovelMapper.paraImovelResponse(imovel);
        return ResponseEntity.ok(response);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> excluirImovel(@PathVariable Long id){
//        excluirImovelInputPort.executar(id);
//        return ResponseEntity.noContent().build();
//    }
}
