package com.sislocacao.core.usecase.imovel;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Imovel;
import com.sislocacao.core.repository.IImovelRepository;
import com.sislocacao.ports.input.BuscarImoveisInputPort;

import java.util.List;

@DomainComponent
public class BuscarImoveisUseCase implements BuscarImoveisInputPort {

    private final IImovelRepository imovelRepository;

    public BuscarImoveisUseCase(IImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    @Override
    public List<Imovel> execute() {
        return imovelRepository.buscarImoveis();
    }
}
