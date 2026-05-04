package com.sislocacao.core.repository;

import com.sislocacao.core.domain.model.Inquilino;

import java.util.List;
import java.util.Optional;

public interface IInquilinoRepository {
    Optional<Inquilino> buscarInquilinoPorId(Long id);
    Inquilino salvarInquilino(Inquilino inquilino);
    List<Inquilino> buscarInquilinos();
    void excluirInquilino(Long id);
    Inquilino atualizarInquilino(Inquilino inquilino);
    boolean existePorCpf(String cpf);
    boolean existePorRg(String rg);
}
