package com.sislocacao.core.repository;

import com.sislocacao.core.domain.model.Locador;

import java.util.List;
import java.util.Optional;

public interface ILocadorRepository {
    List<Locador> buscarLocadores();
    Optional<Locador> buscarLocadorPorId(Long id);
    boolean existePorCpf(String cpf);
    boolean existePorRg(String rg);
    boolean existePorCpfEIdDiferente(String cpf, Long id);
    boolean existePorRgEIdDiferente(String rg, Long id);
    Locador salvarLocador(Locador locador);
    Locador atualizarLocador(Locador locador);
    void excluirLocador(Long id);
}
