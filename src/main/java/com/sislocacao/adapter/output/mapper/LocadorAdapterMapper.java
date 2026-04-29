package com.sislocacao.adapter.output.mapper;

import com.sislocacao.adapter.output.repository.jpa.entity.LocadorEntity;
import com.sislocacao.core.domain.model.Locador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocadorAdapterMapper {
    Locador paraLocador(LocadorEntity locadorEntity);
    LocadorEntity paraLocadorEntity(Locador locador);
}
