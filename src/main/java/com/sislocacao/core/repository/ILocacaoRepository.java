package com.sislocacao.core.repository;

import com.sislocacao.core.domain.model.Locacao;

import java.util.List;
import java.util.Optional;

public interface ILocacaoRepository {
    Locacao salvarLocacao(Locacao locacao);
    Optional<Locacao> buscarLocacaoPorIdImovel(Long id);
    List<Locacao> buscarLocacoes();
    void excluirLocacao(long id);
    Locacao buscarLocacaoPorId(Long id);
}
