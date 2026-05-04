package com.sislocacao.adapter.output.mapper;

import com.sislocacao.adapter.output.repository.jpa.entity.EnderecoEntity;
import com.sislocacao.core.domain.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoAdapterMapper {

    default EnderecoEntity paraEnderecoEntity(Endereco endereco) {
        if (endereco == null) return null;
        EnderecoEntity entity = new EnderecoEntity();
        entity.setLogradouro(endereco.getLogradouro());
        entity.setEstado(endereco.getEstado());
        entity.setBairro(endereco.getBairro());
        entity.setCidade(endereco.getCidade());
        entity.setCep(endereco.getCep());
        return entity;
    }

    default Endereco paraEndereco(EnderecoEntity entity) {
        if (entity == null) return null;
        return new Endereco(entity.getLogradouro(), entity.getEstado(), entity.getBairro(), entity.getCidade(), entity.getCep());
    }
}
