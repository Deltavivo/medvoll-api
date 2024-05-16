package med.voll.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import med.voll.api.domain.model.Consulta;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultaDTO(
        Long idConsulta,
        Long idMedico,
        Long idPaciente,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime horarioConsulta) {

        public DadosDetalhamentoConsultaDTO(Consulta consulta) {
                this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
        }
}
