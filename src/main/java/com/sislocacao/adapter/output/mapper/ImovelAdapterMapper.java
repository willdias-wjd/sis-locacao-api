package com.sislocacao.adapter.output.mapper;

import com.sislocacao.adapter.output.repository.jpa.entity.ImovelEntity;
import com.sislocacao.core.domain.model.Imovel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EnderecoAdapterMapper.class})
public interface ImovelAdapterMapper {

    @Mapping(target = "locacoes", ignore = true)
    Imovel paraImovel(ImovelEntity imovel);

    @Mapping(target = "locacoes", ignore = true)
    ImovelEntity paraImovelEntity(Imovel imovel);
}
