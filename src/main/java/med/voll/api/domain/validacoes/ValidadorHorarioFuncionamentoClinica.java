package med.voll.api.domain.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.dto.DadosAgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas {

    public void validar(DadosAgendamentoConsultaDTO dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoClinica = dataConsulta.getHour() > 18;

        if(domingo || antesAberturaClinica || depoisDoEncerramentoClinica){
            throw new ValidacaoException("Consulta fora do horario de atendimento da clinica.");
        }
    }
}
