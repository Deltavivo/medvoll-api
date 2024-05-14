package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDTO endereco) {
}
