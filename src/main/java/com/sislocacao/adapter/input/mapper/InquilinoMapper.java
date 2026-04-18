package com.sislocacao.adapter.input.mapper;

import com.sislocacao.adapter.input.dto.request.SalvarInquilinoRequest;
import com.sislocacao.adapter.input.dto.response.InquilinoResponse;
import com.sislocacao.core.domain.model.Inquilino;
import com.sislocacao.core.usecase.inquilino.command.SalvarInquilinoCommand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InquilinoMapper {
    SalvarInquilinoCommand paraSalvarInquilinoCommand(SalvarInquilinoRequest request);
    InquilinoResponse paraInquilinoResponse(Inquilino inquilino);
    List<InquilinoResponse> paraInquilinosResponse(List<Inquilino> inquilinos);
}
