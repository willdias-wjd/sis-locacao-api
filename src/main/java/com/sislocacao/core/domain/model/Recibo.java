package com.sislocacao.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Recibo {
    private Long id;

    private String numeroRecibo;
    private BigDecimal valorEnergia;
    private BigDecimal valorAgua;
    private BigDecimal valorJuros;
    private BigDecimal valorTotal;
    private String valorTotalPorExtenso;
    private LocalDate dataInicio;
    private LocalDate dataTermino;

    private Locacao locacao;
}
