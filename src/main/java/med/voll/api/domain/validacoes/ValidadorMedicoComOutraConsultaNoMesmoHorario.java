package med.voll.api.domain.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsultaDTO dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if(medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Medico ja possui outra consulta agendada no mesmo horario.");
        }
    }
}
