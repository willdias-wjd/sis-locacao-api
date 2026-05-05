package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.TipoContrato;

import java.util.List;

public interface BuscarTiposContratoInputPort {
    List<TipoContrato> executar();
}
