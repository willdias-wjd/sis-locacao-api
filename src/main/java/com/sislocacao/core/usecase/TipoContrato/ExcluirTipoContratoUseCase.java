package com.sislocacao.core.usecase.TipoContrato;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.repository.ITipoContratoRepository;
import com.sislocacao.ports.input.BuscarTipoContratoPorIdInputPort;
import com.sislocacao.ports.input.ExcluirTipoContratoInputPort;

@DomainComponent
public class ExcluirTipoContratoUseCase implements ExcluirTipoContratoInputPort {

    private final ITipoContratoRepository tipoContratoRepository;
    private final BuscarTipoContratoPorIdInputPort buscarTipoContratoPorIdInputPort;

    public ExcluirTipoContratoUseCase(ITipoContratoRepository tipoContratoRepository,
                                      BuscarTipoContratoPorIdInputPort buscarTipoContratoPorIdInputPort) {
        this.tipoContratoRepository = tipoContratoRepository;
        this.buscarTipoContratoPorIdInputPort = buscarTipoContratoPorIdInputPort;
    }

    @Override
    public void executar(Long id) {
        buscarTipoContratoPorIdInputPort.executar(id);
        tipoContratoRepository.excluir(id);
    }
}
