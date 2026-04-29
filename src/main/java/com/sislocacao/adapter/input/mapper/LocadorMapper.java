package com.sislocacao.adapter.input.mapper;

import com.sislocacao.adapter.input.dto.request.SalvarLocadorRequest;
import com.sislocacao.adapter.input.dto.response.LocadorResponse;
import com.sislocacao.core.domain.model.Locador;
import com.sislocacao.core.usecase.locador.command.SalvarLocadorCommand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocadorMapper {
    SalvarLocadorCommand paraSalvarLocadorCommand(SalvarLocadorRequest request);
    LocadorResponse paraLocadorResponse(Locador locador);
    List<LocadorResponse> paraLocadoresResponse(List<Locador> locadores);
}
