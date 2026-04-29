package com.sislocacao.core.usecase.locador;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Endereco;
import com.sislocacao.core.domain.model.Locador;
import com.sislocacao.core.exception.BusinessException;
import com.sislocacao.core.repository.ILocadorRepository;
import com.sislocacao.core.usecase.locador.command.SalvarLocadorCommand;
import com.sislocacao.ports.input.AtualizarLocadorInputPort;
import com.sislocacao.ports.input.BuscarLocadorPorIdInputPort;

@DomainComponent
public class AtualizarLocadorUseCase implements AtualizarLocadorInputPort {

    private final ILocadorRepository locadorRepository;
    private final BuscarLocadorPorIdInputPort buscarLocadorPorIdInputPort;

    public AtualizarLocadorUseCase(ILocadorRepository locadorRepository,
                                   BuscarLocadorPorIdInputPort buscarLocadorPorIdInputPort) {
        this.locadorRepository = locadorRepository;
        this.buscarLocadorPorIdInputPort = buscarLocadorPorIdInputPort;
    }

    @Override
    public Locador executar(SalvarLocadorCommand command, Long idLocador) {
        Locador existente = buscarLocadorPorIdInputPort.execute(idLocador);

        if (locadorRepository.existePorCpfEIdDiferente(command.getCpf(), idLocador)) {
            throw new BusinessException("Já existe um locador cadastrado com o CPF: " + command.getCpf());
        }
        if (locadorRepository.existePorRgEIdDiferente(command.getRg(), idLocador)) {
            throw new BusinessException("Já existe um locador cadastrado com o RG: " + command.getRg());
        }

        Endereco endereco = new Endereco(
                command.getLogradouro(),
                command.getEstado(),
                command.getBairro(),
                command.getCidade(),
                command.getCep()
        );

        Locador atualizado = Locador.builder()
                .id(existente.getId())
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
                .status(existente.getStatus())
                .endereco(endereco)
                .build();

        return locadorRepository.atualizarLocador(atualizado);
    }
}
