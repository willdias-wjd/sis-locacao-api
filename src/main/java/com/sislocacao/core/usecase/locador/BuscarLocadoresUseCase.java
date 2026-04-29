package com.sislocacao.core.usecase.locador;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Locador;
import com.sislocacao.core.repository.ILocadorRepository;
import com.sislocacao.ports.input.BuscarLocadoresInputPort;

import java.util.List;

@DomainComponent
public class BuscarLocadoresUseCase implements BuscarLocadoresInputPort {

    private final ILocadorRepository locadorRepository;

    public BuscarLocadoresUseCase(ILocadorRepository locadorRepository) {
        this.locadorRepository = locadorRepository;
    }

    @Override
    public List<Locador> execute() {
        return locadorRepository.buscarLocadores();
    }
}
