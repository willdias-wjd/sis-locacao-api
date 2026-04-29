package com.sislocacao.core.usecase.locador;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Endereco;
import com.sislocacao.core.domain.model.Locador;
import com.sislocacao.core.exception.BusinessException;
import com.sislocacao.core.repository.ILocadorRepository;
import com.sislocacao.core.usecase.locador.command.SalvarLocadorCommand;
import com.sislocacao.ports.input.SalvarLocadorInputPort;

@DomainComponent
public class SalvarLocadorUseCase implements SalvarLocadorInputPort {

    private final ILocadorRepository locadorRepository;

    public SalvarLocadorUseCase(ILocadorRepository locadorRepository) {
        this.locadorRepository = locadorRepository;
    }

    @Override
    public Locador executar(SalvarLocadorCommand command) {
        if (locadorRepository.existePorCpf(command.getCpf())) {
            throw new BusinessException("Já existe um locador cadastrado com o CPF: " + command.getCpf());
        }
        if (locadorRepository.existePorRg(command.getRg())) {
            throw new BusinessException("Já existe um locador cadastrado com o RG: " + command.getRg());
        }

        Endereco endereco = new Endereco(
                command.getLogradouro(),
                command.getEstado(),
                command.getBairro(),
                command.getCidade(),
                command.getCep()
        );

        Locador locador = Locador.builder()
                .nome(command.getNome())
                .sobrenome(command.getSobrenome())
                .cpf(command.getCpf())
                .rg(command.getRg())
                .telefone(command.getTelefone())
                .email(command.getEmail())
                .nacionalidade(command.getNacionalidade())
                .estadoCivil(command.getEstadoCivil())
                .profissao(command.getProfissao())
                .genero(command.getGenero())
                .dataNascimento(command.getDataNascimento())
                .status(Boolean.TRUE)
                .endereco(endereco)
                .build();

        return locadorRepository.salvarLocador(locador);
    }
}
