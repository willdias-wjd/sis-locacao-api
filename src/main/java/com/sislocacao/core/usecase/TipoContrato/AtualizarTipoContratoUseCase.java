package com.sislocacao.core.usecase.TipoContrato;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.TipoContrato;
import com.sislocacao.core.repository.ITipoContratoRepository;
import com.sislocacao.core.usecase.TipoContrato.command.SalvarTipoContratoCommand;
import com.sislocacao.ports.input.AtualizarTipoContratoInputPort;
import com.sislocacao.ports.input.BuscarTipoContratoPorIdInputPort;

import java.time.LocalDateTime;

@DomainComponent
public class AtualizarTipoContratoUseCase implements AtualizarTipoContratoInputPort {

    private final ITipoContratoRepository tipoContratoRepository;
    private final BuscarTipoContratoPorIdInputPort buscarTipoContratoPorIdInputPort;

    public AtualizarTipoContratoUseCase(ITipoContratoRepository tipoContratoRepository,
                                        BuscarTipoContratoPorIdInputPort buscarTipoContratoPorIdInputPort) {
        this.tipoContratoRepository = tipoContratoRepository;
        this.buscarTipoContratoPorIdInputPort = buscarTipoContratoPorIdInputPort;
    }

    @Override
    public TipoContrato executar(SalvarTipoContratoCommand command, Long id) {
        TipoContrato existente = buscarTipoContratoPorIdInputPort.executar(id);

        TipoContrato atualizado = new TipoContrato(
                existente.getId(),
                command.getNome(),
                command.getDescricao(),
                command.getNomeArquivo(),
                existente.getAtivo(),
                existente.getdataCriacao(),
                LocalDateTime.now()
        );

        return tipoContratoRepository.atualizar(atualizado);
    }
}
