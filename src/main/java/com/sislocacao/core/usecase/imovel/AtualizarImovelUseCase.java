package com.sislocacao.core.usecase.imovel;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Imovel;
import com.sislocacao.core.repository.IImovelRepository;
import com.sislocacao.core.usecase.imovel.command.SalvarImovelCommand;
import com.sislocacao.ports.input.AtualizarImovelInputPort;
import com.sislocacao.ports.input.BuscarImovelPorIdInputPort;

import java.util.ArrayList;

@DomainComponent
public class AtualizarImovelUseCase implements AtualizarImovelInputPort {

    private final IImovelRepository imovelRepository;
    private final BuscarImovelPorIdInputPort buscarImovelPorIdInputPort;

    public AtualizarImovelUseCase(IImovelRepository imovelRepository, BuscarImovelPorIdInputPort buscarImovelPorIdInputPort) {
        this.imovelRepository = imovelRepository;
        this.buscarImovelPorIdInputPort = buscarImovelPorIdInputPort;
    }

    @Override
    public Imovel executar(SalvarImovelCommand command, Long idImovel) {
        Imovel existente = buscarImovelPorIdInputPort.execute(idImovel);

        Imovel atualizado = criarImovelFactory(command, existente);

        return imovelRepository.atualizarImovel(atualizado);
    }

    private static Imovel criarImovelFactory(SalvarImovelCommand command, Imovel existente) {
        Imovel atualizado = new Imovel(
                existente.getId(),
                command.getDescricao(),
                command.getGaragem(),
                command.getComodos(),
                command.getNumero(),
                existente.getStatus(),
                existente.getLocacoes() == null ? new ArrayList<>() : existente.getLocacoes(),
                existente.getEndereco()
        );
        return atualizado;
    }
}

