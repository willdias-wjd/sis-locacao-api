package com.sislocacao.adapter.input.mapper;

import com.sislocacao.adapter.input.dto.request.EnderecoRequest;
import com.sislocacao.adapter.input.dto.request.SalvarImovelRequest;
import com.sislocacao.adapter.input.dto.response.EnderecoResponse;
import com.sislocacao.adapter.input.dto.response.ImovelResponse;
import com.sislocacao.core.domain.model.Endereco;
import com.sislocacao.core.domain.model.Imovel;
import com.sislocacao.core.usecase.imovel.command.EnderecoCommand;
import com.sislocacao.core.usecase.imovel.command.SalvarImovelCommand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImovelMapper {
    EnderecoCommand paraEnderecoCommand(EnderecoRequest request);
    EnderecoResponse paraEnderecoResponse(Endereco endereco);
    SalvarImovelCommand paraSalvarImovelCommand(SalvarImovelRequest request);
    ImovelResponse paraImovelResponse(Imovel imovel);
    List<ImovelResponse> paraImoveisResponse(List<Imovel> imoveis);
}
