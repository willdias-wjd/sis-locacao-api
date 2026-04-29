package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.Imovel;
import com.sislocacao.core.usecase.imovel.command.SalvarImovelCommand;

public interface SalvarImovelInputPort {
    Imovel executar(SalvarImovelCommand command);
}
