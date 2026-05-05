package com.sislocacao.adapter.input.dto.response;

public record TipoContratoResponse(
        Long id,
        String nome,
        String descricao,
        String nomeArquivo,
        Boolean ativo,
        String dataCriacao,
        String dataAtualizacao
) {
}
