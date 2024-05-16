package med.voll.api.domain.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.dto.DadosAgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsultas{

    public void validar(DadosAgendamentoConsultaDTO dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaDeMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaDeMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedencia minima de 30 minutos.");
        }
    }
}
