package med.voll.api.domain.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPacienteDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDTO endereco) {
}
