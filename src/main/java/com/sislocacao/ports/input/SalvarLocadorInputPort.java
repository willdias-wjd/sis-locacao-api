package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.Locador;
import com.sislocacao.core.usecase.locador.command.SalvarLocadorCommand;

public interface SalvarLocadorInputPort {
    Locador executar(SalvarLocadorCommand command);
}
