package com.sislocacao.adapter.output.repository.impl;

import com.sislocacao.adapter.output.mapper.ImovelAdapterMapper;
import com.sislocacao.adapter.output.repository.jpa.ImovelJpaRepository;
import com.sislocacao.adapter.output.repository.jpa.entity.ImovelEntity;
import com.sislocacao.core.domain.model.Imovel;
import com.sislocacao.core.repository.IImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ImovelRepositoryAdapter implements IImovelRepository {

    @Autowired
    private ImovelJpaRepository imovelJpaRepository;

    @Autowired
    private ImovelAdapterMapper imovelAdapterMapper;

    @Override
    public Optional<Imovel> buscarImovelPorId(Long id) {
        return imovelJpaRepository.findById(id)
                .map(imovelAdapterMapper::paraImovel);
    }

    @Override
    public Imovel salvarImovel(Imovel imovel) {
        ImovelEntity entity = imovelAdapterMapper.paraImovelEntity(imovel);
        ImovelEntity saved = imovelJpaRepository.save(entity);
        return imovelAdapterMapper.paraImovel(saved);
    }

    @Override
    public List<Imovel> buscarImoveis() {
        return imovelJpaRepository.findAll()
                .stream()
                .map(imovelAdapterMapper::paraImovel)
                .collect(Collectors.toList());
    }

    @Override
    public void excluirImovel(Long id) {
        imovelJpaRepository.findById(id).ifPresent(entity -> {
            entity.setStatus(Boolean.FALSE);
            imovelJpaRepository.save(entity);
        });
    }

    @Override
    public Imovel atualizarImovel(Imovel imovel) {
        ImovelEntity entity = imovelAdapterMapper.paraImovelEntity(imovel);
        ImovelEntity updated = imovelJpaRepository.save(entity);
        return imovelAdapterMapper.paraImovel(updated);
    }
}
