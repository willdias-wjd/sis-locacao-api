package com.sislocacao.adapter.input.dto.response;

public record ImovelResponse(
        Long id,
        String descricao,
        Boolean garagem,
        Integer comodos,
        String numero,
        Boolean status
) {
}
