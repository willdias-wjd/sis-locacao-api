package com.sislocacao.adapter.input.dto.request;

public record SalvarTipoContratoRequest(
        String nome,
        String descricao,
        String nomeArquivo
) {
}
