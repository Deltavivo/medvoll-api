package med.voll.api.domain.dto;

import med.voll.api.domain.model.Endereco;
import med.voll.api.domain.model.Paciente;

public record DadosDetalhamentoPacienteDTO(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DadosDetalhamentoPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
