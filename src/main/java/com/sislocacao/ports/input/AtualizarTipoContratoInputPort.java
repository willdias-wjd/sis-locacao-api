package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.TipoContrato;
import com.sislocacao.core.usecase.TipoContrato.command.SalvarTipoContratoCommand;

public interface AtualizarTipoContratoInputPort {
    TipoContrato executar(SalvarTipoContratoCommand command, Long id);
}
