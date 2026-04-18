package com.sislocacao.core.usecase.inquilino;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Inquilino;
import com.sislocacao.core.repository.IInquilinoRepository;
import com.sislocacao.ports.input.BuscarInquilinosInputPort;

import java.util.List;

@DomainComponent
public class BuscarInquilinosUseCase implements BuscarInquilinosInputPort {

    private final IInquilinoRepository inquilinoRepository;

    public BuscarInquilinosUseCase(IInquilinoRepository inquilinoRepository) {
        this.inquilinoRepository = inquilinoRepository;
    }

    @Override
    public List<Inquilino> execute() {
        return inquilinoRepository.buscarInquilinos();
    }
}
