package com.sislocacao.core.repository;

import com.sislocacao.core.domain.model.TipoContrato;

import java.util.List;
import java.util.Optional;

public interface ITipoContratoRepository {
    TipoContrato salvarTipoContrato(TipoContrato tipoContrato);
    Optional<TipoContrato> buscarPorId(Long id);
    List<TipoContrato> listarTodos();
    TipoContrato atualizar(TipoContrato tipoContrato);
    void excluir(Long id);
}
