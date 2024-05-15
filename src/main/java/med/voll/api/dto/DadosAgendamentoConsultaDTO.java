package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import med.voll.api.model.Especialidade;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsultaDTO(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Especialidade especialidade) {
}