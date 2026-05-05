package com.sislocacao.adapter.input.mapper;

import com.sislocacao.adapter.input.dto.request.SalvarTipoContratoRequest;
import com.sislocacao.adapter.input.dto.response.TipoContratoResponse;
import com.sislocacao.core.domain.model.TipoContrato;
import com.sislocacao.core.usecase.TipoContrato.command.SalvarTipoContratoCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoContratoMapper {
        SalvarTipoContratoCommand paraSalvarTipoContratoCommand(SalvarTipoContratoRequest request);
        TipoContratoResponse paraTipoContratoResponse(TipoContrato tipoContrato);
}
