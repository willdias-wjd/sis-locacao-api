package com.sislocacao.core.usecase.inquilino;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.repository.IInquilinoRepository;
import com.sislocacao.ports.input.BuscarInquilinoPorIdInputPort;
import com.sislocacao.ports.input.ExcluirInquilinoInputPort;

@DomainComponent
public class ExcluirInquilinoUseCase implements ExcluirInquilinoInputPort {

    private final IInquilinoRepository inquilinoRepository;
    private final BuscarInquilinoPorIdInputPort buscarInquilinoPorIdInputPort;

    public ExcluirInquilinoUseCase(IInquilinoRepository inquilinoRepository, BuscarInquilinoPorIdInputPort buscarInquilinoPorIdInputPort) {
        this.inquilinoRepository = inquilinoRepository;
        this.buscarInquilinoPorIdInputPort = buscarInquilinoPorIdInputPort;
    }

    @Override
    public void executar(Long id) {
        buscarInquilinoPorIdInputPort.executar(id);
        inquilinoRepository.excluirInquilino(id);
    }
}
