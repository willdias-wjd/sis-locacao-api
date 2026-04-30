package com.sislocacao.core.usecase.inquilino;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Inquilino;
import com.sislocacao.core.repository.IInquilinoRepository;
import com.sislocacao.core.usecase.inquilino.command.SalvarInquilinoCommand;
import com.sislocacao.ports.input.SalvarInquilinoInputPort;

import java.util.ArrayList;

@DomainComponent
public class SalvarInquilinoUseCase implements SalvarInquilinoInputPort {

    private final IInquilinoRepository inquilinoRepository;

    public SalvarInquilinoUseCase(IInquilinoRepository inquilinoRepository) {
        this.inquilinoRepository = inquilinoRepository;
    }

    @Override
    public Inquilino executar(SalvarInquilinoCommand command) {
        Inquilino inquilino = criarInquilinoFactory(command);
        return inquilinoRepository.salvarInquilino(inquilino);
    }

    private static Inquilino criarInquilinoFactory(SalvarInquilinoCommand command) {
        Inquilino inquilino = new Inquilino(
                null,
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
        return inquilino;
    }
}
