package com.sislocacao.adapter.input.dto.request;

public record SalvarImovelRequest(
        String descricao,
        Boolean garagem,
        Integer comodos,
        String numero,
        Long enderecoId
) { }
