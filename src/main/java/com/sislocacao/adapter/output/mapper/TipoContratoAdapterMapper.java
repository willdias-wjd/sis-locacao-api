package com.sislocacao.adapter.output.mapper;

import com.sislocacao.adapter.output.repository.jpa.entity.TipoContratoEntity;
import com.sislocacao.core.domain.model.TipoContrato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoContratoAdapterMapper {
    TipoContratoEntity mapToEntity(TipoContrato tipoContrato);
    TipoContrato mapToDomain(TipoContratoEntity tipoContratoEntity);
}
