package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.Inquilino;
import com.sislocacao.core.usecase.inquilino.command.SalvarInquilinoCommand;

public interface AtualizarInquilinoInputPort {
    Inquilino executar(SalvarInquilinoCommand command, Long idInquilino);
}
