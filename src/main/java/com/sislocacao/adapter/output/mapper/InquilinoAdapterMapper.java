package com.sislocacao.adapter.output.mapper;

import com.sislocacao.adapter.output.repository.jpa.entity.InquilinoEntity;
import com.sislocacao.core.domain.model.Inquilino;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InquilinoAdapterMapper {

    @Mapping(target = "locacoesEntity", ignore = true)
    Inquilino paraInquilino(InquilinoEntity inquilino);

    @Mapping(target = "locacoesEntity", ignore = true)
    InquilinoEntity paraInquilinoEntity(Inquilino inquilino);
}
