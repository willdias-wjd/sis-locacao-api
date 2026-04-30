package com.sislocacao.core.usecase.inquilino;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Inquilino;
import com.sislocacao.core.repository.IInquilinoRepository;
import com.sislocacao.core.usecase.inquilino.command.SalvarInquilinoCommand;
import com.sislocacao.ports.input.AtualizarInquilinoInputPort;
import com.sislocacao.ports.input.BuscarInquilinoPorIdInputPort;

import java.util.ArrayList;

@DomainComponent
public class AtualizarInquilinoUseCase implements AtualizarInquilinoInputPort {

    private final IInquilinoRepository inquilinoRepository;
    private final BuscarInquilinoPorIdInputPort buscarInquilinoPorIdInputPort;

    public AtualizarInquilinoUseCase(IInquilinoRepository inquilinoRepository, BuscarInquilinoPorIdInputPort buscarInquilinoPorIdInputPort) {
        this.inquilinoRepository = inquilinoRepository;
        this.buscarInquilinoPorIdInputPort = buscarInquilinoPorIdInputPort;
    }

    @Override
    public Inquilino executar(SalvarInquilinoCommand command, Long idInquilino) {
        Inquilino existente = buscarInquilinoPorIdInputPort.executar(idInquilino);

        Inquilino atualizado = criarInquilinoFactory(command, existente);

        return inquilinoRepository.atualizarInquilino(atualizado);
    }

    private static Inquilino criarInquilinoFactory(SalvarInquilinoCommand command, Inquilino existente) {
        Inquilino atualizado = new Inquilino(
                existente.getId(),
                command.getNome(),
                command.getSobrenome(),
                command.getCpf(),
                command.getRg(),
                command.getTelefone(),
                command.getEmail(),
                command.getNacionalidade(),
                command.getEstadoCivil(),
                command.getProfissao(),
                command.getGenero(),
                command.getDataNascimento(),
                Boolean.TRUE
        );
        return atualizado;
    }
}
