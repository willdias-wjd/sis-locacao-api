package com.sislocacao.core.usecase.TipoContrato;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.TipoContrato;
import com.sislocacao.core.exception.ResourceNotFoundException;
import com.sislocacao.core.repository.ITipoContratoRepository;
import com.sislocacao.ports.input.BuscarTipoContratoPorIdInputPort;

@DomainComponent
public class BuscarTipoContratoPorIdUseCase implements BuscarTipoContratoPorIdInputPort {

    private final ITipoContratoRepository tipoContratoRepository;

    public BuscarTipoContratoPorIdUseCase(ITipoContratoRepository tipoContratoRepository) {
        this.tipoContratoRepository = tipoContratoRepository;
    }

    @Override
    public TipoContrato executar(Long id) {
        return tipoContratoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de contrato não encontrado para o id: " + id));
    }
}
