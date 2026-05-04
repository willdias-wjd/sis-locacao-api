package com.sislocacao.core.usecase.imovel;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Endereco;
import com.sislocacao.core.domain.model.Imovel;
import com.sislocacao.core.repository.IImovelRepository;
import com.sislocacao.core.usecase.imovel.command.EnderecoCommand;
import com.sislocacao.core.usecase.imovel.command.SalvarImovelCommand;
import com.sislocacao.ports.input.SalvarImovelInputPort;

import java.util.ArrayList;

@DomainComponent
public class SalvarImovelUseCase implements SalvarImovelInputPort {

    private final IImovelRepository imovelRepository;

    public SalvarImovelUseCase(IImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    @Override
    public Imovel executar(SalvarImovelCommand command) {
        Imovel imovel = criarImovelFactory(command);
        return imovelRepository.salvarImovel(imovel);
    }

    private static Imovel criarImovelFactory(SalvarImovelCommand command) {
        EnderecoCommand ec = command.getEndereco();
        Endereco endereco = new Endereco(ec.getLogradouro(), ec.getEstado(), ec.getBairro(), ec.getCidade(), ec.getCep());
        return new Imovel(null, command.getDescricao(), command.getGaragem(), command.getComodos(), command.getNumero(), Boolean.TRUE, new ArrayList<>(), endereco);
    }
}
