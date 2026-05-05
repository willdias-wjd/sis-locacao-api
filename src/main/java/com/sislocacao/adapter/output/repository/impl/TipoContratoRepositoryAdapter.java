package com.sislocacao.adapter.output.repository.impl;

import com.sislocacao.adapter.output.mapper.TipoContratoAdapterMapper;
import com.sislocacao.adapter.output.repository.jpa.TipoContratoJpaRespository;
import com.sislocacao.adapter.output.repository.jpa.entity.TipoContratoEntity;
import com.sislocacao.core.domain.model.TipoContrato;
import com.sislocacao.core.repository.ITipoContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TipoContratoRepositoryAdapter implements ITipoContratoRepository {

    @Autowired
    private TipoContratoJpaRespository tipoContratoJpaRespository;

    @Autowired
    private TipoContratoAdapterMapper tipoContratoAdapterMapper;

    @Override
    public TipoContrato salvarTipoContrato(TipoContrato tipoContrato) {
        TipoContratoEntity entity = tipoContratoAdapterMapper.mapToEntity(tipoContrato);
        TipoContratoEntity saved = tipoContratoJpaRespository.save(entity);
        return tipoContratoAdapterMapper.mapToDomain(saved);
    }

    @Override
    public Optional<TipoContrato> buscarPorId(Long id) {
        return tipoContratoJpaRespository.findById(id)
                .map(tipoContratoAdapterMapper::mapToDomain);
    }

    @Override
    public List<TipoContrato> listarTodos() {
        return tipoContratoJpaRespository.findAll().stream()
                .map(tipoContratoAdapterMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public TipoContrato atualizar(TipoContrato tipoContrato) {
        TipoContratoEntity entity = tipoContratoAdapterMapper.mapToEntity(tipoContrato);
        TipoContratoEntity saved = tipoContratoJpaRespository.save(entity);
        return tipoContratoAdapterMapper.mapToDomain(saved);
    }

    @Override
    public void excluir(Long id) {
        tipoContratoJpaRespository.deleteById(id);
    }
}
