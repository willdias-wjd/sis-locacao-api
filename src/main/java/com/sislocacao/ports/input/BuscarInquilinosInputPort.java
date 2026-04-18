package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.Inquilino;

import java.util.List;

public interface BuscarInquilinosInputPort {
    List<Inquilino> execute();
}
