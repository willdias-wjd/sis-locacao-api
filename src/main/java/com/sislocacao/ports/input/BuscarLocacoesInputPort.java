package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.Locacao;

import java.util.List;

public interface BuscarLocacoesInputPort {
    List<Locacao> execute();
}
