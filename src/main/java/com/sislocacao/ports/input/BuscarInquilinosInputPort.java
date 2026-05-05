package com.sislocacao.ports.input;

import com.sislocacao.core.domain.model.Inquilino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscarInquilinosInputPort {
    Page<Inquilino> execute(String nome, String cpf, Boolean status, Pageable pageable);
}
