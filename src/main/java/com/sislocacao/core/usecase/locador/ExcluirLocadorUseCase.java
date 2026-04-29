package com.sislocacao.core.usecase.locador;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Locador;
import com.sislocacao.core.repository.ILocacaoRepository;
import com.sislocacao.core.repository.ILocadorRepository;
import com.sislocacao.ports.input.BuscarLocadorPorIdInputPort;
import com.sislocacao.ports.input.ExcluirLocadorInputPort;

@DomainComponent
public class ExcluirLocadorUseCase implements ExcluirLocadorInputPort {

    private final ILocadorRepository locadorRepository;
    private final ILocacaoRepository locacaoRepository;
    private final BuscarLocadorPorIdInputPort buscarLocadorPorIdInputPort;

    public ExcluirLocadorUseCase(ILocadorRepository locadorRepository,
                                 ILocacaoRepository locacaoRepository,
                                 BuscarLocadorPorIdInputPort buscarLocadorPorIdInputPort) {
        this.locadorRepository = locadorRepository;
        this.locacaoRepository = locacaoRepository;
        this.buscarLocadorPorIdInputPort = buscarLocadorPorIdInputPort;
    }

    @Override
    public void executar(Long id) {
        Locador locador = buscarLocadorPorIdInputPort.execute(id);

        if (locacaoRepository.existeLocacaoPorLocadorId(id)) {
            locadorRepository.atualizarLocador(locador.inativar());
        } else {
            locadorRepository.excluirLocador(id);
        }
    }
}
