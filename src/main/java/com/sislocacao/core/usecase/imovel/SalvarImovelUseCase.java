package com.sislocacao.core.usecase.imovel;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Imovel;
import com.sislocacao.core.repository.IImovelRepository;
import com.sislocacao.core.usecase.imovel.command.SalvarImovelCommand;
import com.sislocacao.ports.input.SalvarImovelInputPort;

import java.util.ArrayList;

@DomainComponent
public class SalvarImovelUseCase implements SalvarImovelInputPort {

    private final IImovelRepository imovelRepository;

    public SalvarImovelUseCase(IImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    @Override
    public Imovel executar(SalvarImovelCommand command) {
        Imovel imovel = criarImovelFactory(command);
        return imovelRepository.salvarImovel(imovel);
    }

    private static Imovel criarImovelFactory(SalvarImovelCommand command) {
        Imovel imovel = new Imovel(
                null,
                command.getDescricao(),
                command.getGaragem(),
                command.getComodos(),
                command.getNumero(),
                Boolean.TRUE,
                new ArrayList<>(),
                null
        );
        return imovel;
    }
}
