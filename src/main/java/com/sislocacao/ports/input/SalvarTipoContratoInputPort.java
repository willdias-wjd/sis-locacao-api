package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.TipoContrato;
import com.sislocacao.core.usecase.TipoContrato.command.SalvarTipoContratoCommand;

/**
 * The interface Salvar tipo contrato input port.
 */
public interface SalvarTipoContratoInputPort {
    /**
     * Executar tipo contrato.
     *
     * @param command the command
     * @return the tipo contrato
     */
    TipoContrato executar(SalvarTipoContratoCommand command);
}
