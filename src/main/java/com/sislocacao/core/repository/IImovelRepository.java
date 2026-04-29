package com.sislocacao.core.repository;

import com.sislocacao.core.domain.model.Imovel;

import java.util.List;
import java.util.Optional;

public interface IImovelRepository {
    Optional<Imovel> buscarImovelPorId(Long id);
    Imovel salvarImovel(Imovel imovel);
    List<Imovel> buscarImoveis();
    void excluirImovel(Long id);
    Imovel atualizarImovel(Imovel imovel);
}
