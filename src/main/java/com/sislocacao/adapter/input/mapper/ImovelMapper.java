package com.sislocacao.adapter.input.mapper;

import com.sislocacao.adapter.input.dto.request.SalvarImovelRequest;
import com.sislocacao.adapter.input.dto.response.ImovelResponse;
import com.sislocacao.core.domain.model.Imovel;
import com.sislocacao.core.usecase.imovel.command.SalvarImovelCommand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImovelMapper {
    SalvarImovelCommand paraSalvarImovelCommand(SalvarImovelRequest request);
    ImovelResponse paraImovelResponse(Imovel imovel);
    List<ImovelResponse> paraImoveisResponse(List<Imovel> imoveis);
}
