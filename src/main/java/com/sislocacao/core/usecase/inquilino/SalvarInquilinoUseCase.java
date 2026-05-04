package com.sislocacao.core.usecase.inquilino;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Inquilino;
import com.sislocacao.core.exception.BusinessException;
import com.sislocacao.core.repository.IInquilinoRepository;
import com.sislocacao.core.usecase.inquilino.command.SalvarInquilinoCommand;
import com.sislocacao.ports.input.BuscarInquilinoPorIdInputPort;
import com.sislocacao.ports.input.SalvarInquilinoInputPort;

import java.util.ArrayList;

@DomainComponent
public class SalvarInquilinoUseCase implements SalvarInquilinoInputPort {

    private final IInquilinoRepository inquilinoRepository;
    private final BuscarInquilinoPorIdInputPort buscarInquilinoPorIdInputPort;

    public SalvarInquilinoUseCase(IInquilinoRepository inquilinoRepository, BuscarInquilinoPorIdInputPort buscarInquilinoPorIdInputPort) {
        this.inquilinoRepository = inquilinoRepository;
        this.buscarInquilinoPorIdInputPort = buscarInquilinoPorIdInputPort;
    }

    @Override
    public Inquilino executar(SalvarInquilinoCommand command) {
        Inquilino inquilino = criarInquilinoFactory(command);

       if(inquilinoRepository.existePorCpf(command.getCpf())){
           throw new BusinessException("Já existe um inquilino cadastrado com o CPF: " + command.getCpf());
       }

       if(inquilinoRepository.existePorRg(command.getRg())){
           throw new BusinessException("Já existe um inquilino cadastrado com o RG: " + command.getRg());
       }

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
