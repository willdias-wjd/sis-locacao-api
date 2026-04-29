package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.Imovel;

import java.util.List;

public interface BuscarImoveisInputPort {
    List<Imovel> execute();
}
