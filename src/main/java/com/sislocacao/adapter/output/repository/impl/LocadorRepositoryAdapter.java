package com.sislocacao.adapter.output.repository.impl;

import com.sislocacao.adapter.output.mapper.LocadorAdapterMapper;
import com.sislocacao.adapter.output.repository.jpa.LocadorJpaRepository;
import com.sislocacao.adapter.output.repository.jpa.entity.LocadorEntity;
import com.sislocacao.core.domain.model.Locador;
import com.sislocacao.core.repository.ILocadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LocadorRepositoryAdapter implements ILocadorRepository {

    @Autowired
    private LocadorJpaRepository locadorJpaRepository;

    @Autowired
    private LocadorAdapterMapper locadorAdapterMapper;

    @Override
    public List<Locador> buscarLocadores() {
        return locadorJpaRepository.findAll()
                .stream()
                .map(locadorAdapterMapper::paraLocador)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Locador> buscarLocadorPorId(Long id) {
        return locadorJpaRepository.findById(id)
                .map(locadorAdapterMapper::paraLocador);
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return locadorJpaRepository.existsByCpf(cpf);
    }

    @Override
    public boolean existePorRg(String rg) {
        return locadorJpaRepository.existsByRg(rg);
    }

    @Override
    public boolean existePorCpfEIdDiferente(String cpf, Long id) {
        return locadorJpaRepository.existsByCpfAndIdNot(cpf, id);
    }

    @Override
    public boolean existePorRgEIdDiferente(String rg, Long id) {
        return locadorJpaRepository.existsByRgAndIdNot(rg, id);
    }

    @Override
    public Locador salvarLocador(Locador locador) {
        LocadorEntity entity = locadorAdapterMapper.paraLocadorEntity(locador);
        return locadorAdapterMapper.paraLocador(locadorJpaRepository.save(entity));
    }

    @Override
    public Locador atualizarLocador(Locador locador) {
        LocadorEntity entity = locadorJpaRepository.findById(locador.getId())
                .orElseThrow(() -> new RuntimeException("Locador não encontrado com id: " + locador.getId()));

        criarLocadorEntityFactory(locador, entity);

        return locadorAdapterMapper.paraLocador(locadorJpaRepository.save(entity));
    }

    @Override
    public void excluirLocador(Long id) {
        locadorJpaRepository.deleteById(id);
    }

    private static void criarLocadorEntityFactory(Locador locador, LocadorEntity entity) {
        entity.setNome(locador.getNome());
        entity.setSobrenome(locador.getSobrenome());
        entity.setCpf(locador.getCpf());
        entity.setRg(locador.getRg());
        entity.setTelefone(locador.getTelefone());
        entity.setEmail(locador.getEmail());
        entity.setNacionalidade(locador.getNacionalidade());
        entity.setEstadoCivil(locador.getEstadoCivil());
        entity.setProfissao(locador.getProfissao());
        entity.setGenero(locador.getGenero());
        entity.setDataNascimento(locador.getDataNascimento());
        entity.setStatus(locador.getStatus());

        if (locador.getEndereco() != null && entity.getEndereco() != null) {
            entity.getEndereco().setLogradouro(locador.getEndereco().getLogradouro());
            entity.getEndereco().setEstado(locador.getEndereco().getEstado());
            entity.getEndereco().setBairro(locador.getEndereco().getBairro());
            entity.getEndereco().setCidade(locador.getEndereco().getCidade());
            entity.getEndereco().setCep(locador.getEndereco().getCep());
        }
    }
}
