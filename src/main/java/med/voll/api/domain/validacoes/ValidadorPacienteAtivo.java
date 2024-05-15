package med.voll.api.domain.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsultaDTO dados){
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());

        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta nao pode ser agendada com paciente excluido");
        }
    }
}
