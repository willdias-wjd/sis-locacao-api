package com.sislocacao.adapter.output.repository.impl;

import com.sislocacao.adapter.output.mapper.InquilinoAdapterMapper;
import com.sislocacao.adapter.output.repository.jpa.InquilinoJpaRepository;
import com.sislocacao.adapter.output.repository.jpa.entity.InquilinoEntity;
import com.sislocacao.core.domain.model.Inquilino;
import com.sislocacao.core.repository.IInquilinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InquilinoRepositoryAdapter implements IInquilinoRepository {

    @Autowired
    private InquilinoJpaRepository inquilinoJpaRepository;

    @Autowired
    private InquilinoAdapterMapper inquilinoAdapterMapper;

    @Override
    public Optional<Inquilino> buscarInquilinoPorId(Long id) {
        return inquilinoJpaRepository.findById(id)
                .map(inquilinoAdapterMapper::paraInquilino);
    }

    @Override
    public Inquilino salvarInquilino(Inquilino inquilino) {
        InquilinoEntity entity = inquilinoAdapterMapper.paraInquilinoEntity(inquilino);
        InquilinoEntity saved = inquilinoJpaRepository.save(entity);
        return inquilinoAdapterMapper.paraInquilino(saved);
    }

    @Override
    public List<Inquilino> buscarInquilinos() {
        return inquilinoJpaRepository.findAll()
                .stream()
                .map(inquilinoAdapterMapper::paraInquilino)
                .collect(Collectors.toList());
    }

    @Override
    public void excluirInquilino(Long id) {
        inquilinoJpaRepository.findById(id).ifPresent(entity -> {
            entity.setStatus(Boolean.FALSE);
            inquilinoJpaRepository.save(entity);
        });
    }

    @Override
    public Inquilino atualizarInquilino(Inquilino inquilino) {
        InquilinoEntity entity = inquilinoAdapterMapper.paraInquilinoEntity(inquilino);
        InquilinoEntity updated = inquilinoJpaRepository.save(entity);
        return inquilinoAdapterMapper.paraInquilino(updated);
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return inquilinoJpaRepository.existsByCpf(cpf);
    }

    @Override
    public boolean existePorRg(String rg) {
        return inquilinoJpaRepository.existsByRg(rg);
    }
}
