package com.sislocacao.core.repository;

import com.sislocacao.core.domain.model.Inquilino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IInquilinoRepository {
    Optional<Inquilino> buscarInquilinoPorId(Long id);
    Inquilino salvarInquilino(Inquilino inquilino);
    Page<Inquilino> buscarInquilinos(String nome, String cpf, Boolean status, Pageable pageable);
    void excluirInquilino(Long id);
    Inquilino atualizarInquilino(Inquilino inquilino);
    boolean existePorCpf(String cpf);
    boolean existePorRg(String rg);
}
