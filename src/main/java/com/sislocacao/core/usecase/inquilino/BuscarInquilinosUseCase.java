package com.sislocacao.core.usecase.inquilino;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Inquilino;
import com.sislocacao.core.repository.IInquilinoRepository;
import com.sislocacao.ports.input.BuscarInquilinosInputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@DomainComponent
public class BuscarInquilinosUseCase implements BuscarInquilinosInputPort {

    private final IInquilinoRepository inquilinoRepository;

    public BuscarInquilinosUseCase(IInquilinoRepository inquilinoRepository) {
        this.inquilinoRepository = inquilinoRepository;
    }

    @Override
    public Page<Inquilino> execute(String nome, String cpf, Boolean status, Pageable pageable) {
        return inquilinoRepository.buscarInquilinos(nome, cpf, status, pageable);
    }
}
