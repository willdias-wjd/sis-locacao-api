package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.Locador;

import java.util.List;

public interface BuscarLocadoresInputPort {
    List<Locador> execute();
}
