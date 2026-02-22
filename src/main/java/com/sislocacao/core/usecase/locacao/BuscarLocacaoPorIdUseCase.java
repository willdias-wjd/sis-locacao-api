package com.sislocacao.core.usecase.locacao;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Locacao;
import com.sislocacao.core.repository.ILocacaoRepository;
import com.sislocacao.ports.input.BuscarLocacaoPorIdInputPort;

@DomainComponent
public class BuscarLocacaoPorIdUseCase implements BuscarLocacaoPorIdInputPort {
    private final ILocacaoRepository locacaoRepository;

    public BuscarLocacaoPorIdUseCase(ILocacaoRepository repository) {
        this.locacaoRepository = repository;
    }

    @Override
    public Locacao executar(Long id) {
        return locacaoRepository.buscarLocacaoPorId(id);
    }
}
