package com.sislocacao.core.usecase.locacao;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.repository.ILocacaoRepository;
import com.sislocacao.ports.input.BuscarLocacaoPorIdInputPort;
import com.sislocacao.ports.input.ExcluirLocacaoInputPort;

@DomainComponent
public class ExcluirLocacaoUseCase implements ExcluirLocacaoInputPort {

    private final ILocacaoRepository locacaoRepository;
    private final BuscarLocacaoPorIdInputPort buscarLocacaoPorIdInputPort;

    public ExcluirLocacaoUseCase(ILocacaoRepository repository, BuscarLocacaoPorIdInputPort buscarLocacaoPorIdInputPort) {
        this.locacaoRepository = repository;
        this.buscarLocacaoPorIdInputPort = buscarLocacaoPorIdInputPort;
    }

    @Override
    public void executar(Long id) {
        buscarLocacaoPorIdInputPort.executar(id);
        locacaoRepository.excluirLocacao(id);
    }
}
