package com.sislocacao.core.usecase.TipoContrato;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.TipoContrato;
import com.sislocacao.core.repository.ITipoContratoRepository;
import com.sislocacao.core.usecase.TipoContrato.command.SalvarTipoContratoCommand;
import com.sislocacao.ports.input.SalvarTipoContratoInputPort;

import java.time.LocalDateTime;

@DomainComponent
public class SalvarTipoContratoUseCase implements SalvarTipoContratoInputPort {

    private final ITipoContratoRepository tipoContratoRepository;

    public SalvarTipoContratoUseCase(ITipoContratoRepository tipoContratoRepository) {
        this.tipoContratoRepository = tipoContratoRepository;
    }

    @Override
    public TipoContrato executar(SalvarTipoContratoCommand command) {
        TipoContrato tipoContrato = criarTipoContratoFactory(command);
        return tipoContratoRepository.salvarTipoContrato(tipoContrato);
    }

    private static TipoContrato criarTipoContratoFactory(SalvarTipoContratoCommand command) {
        return new TipoContrato(
                null,
                command.getNome(),
                command.getDescricao(),
                command.getNomeArquivo(),
                Boolean.TRUE,
                LocalDateTime.now(),
                null
        );
    }
}
