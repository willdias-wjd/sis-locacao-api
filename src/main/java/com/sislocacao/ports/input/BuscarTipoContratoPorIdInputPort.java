package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.TipoContrato;

public interface BuscarTipoContratoPorIdInputPort {
    TipoContrato executar(Long id);
}
