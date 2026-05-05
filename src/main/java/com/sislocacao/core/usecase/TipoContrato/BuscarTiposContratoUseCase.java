package com.sislocacao.core.usecase.TipoContrato;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.TipoContrato;
import com.sislocacao.core.repository.ITipoContratoRepository;
import com.sislocacao.ports.input.BuscarTiposContratoInputPort;

import java.util.List;

@DomainComponent
public class BuscarTiposContratoUseCase implements BuscarTiposContratoInputPort {

    private final ITipoContratoRepository tipoContratoRepository;

    public BuscarTiposContratoUseCase(ITipoContratoRepository tipoContratoRepository) {
        this.tipoContratoRepository = tipoContratoRepository;
    }

    @Override
    public List<TipoContrato> executar() {
        return tipoContratoRepository.listarTodos();
    }
}
