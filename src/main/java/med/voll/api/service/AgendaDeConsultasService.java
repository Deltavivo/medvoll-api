package med.voll.api.service;

import med.voll.api.domain.validacoes.ValidadorAgendamentoDeConsultas;
import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.dto.DadosDetalhamentoConsultaDTO;
import med.voll.api.model.Consulta;
import med.voll.api.model.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;

    public DadosDetalhamentoConsultaDTO agendar(DadosAgendamentoConsultaDTO dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado nao existe.");
        }

        if(dados.idMedico()!= null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico informado nao existe.");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        if(medico == null){
            throw new ValidacaoException("Nao existe medico disponivel nessa data e horario.");
        }

        var consulta = new Consulta(null,medico, paciente, dados.data());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsultaDTO(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsultaDTO dados) {
        if(dados.idMedico()!= null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade e obrigatoria quando o medico nao e escolhido.");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
