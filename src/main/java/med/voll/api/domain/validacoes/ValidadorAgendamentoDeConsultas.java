package med.voll.api.domain.validacoes;

import med.voll.api.domain.dto.DadosAgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsultas {

    void validar(DadosAgendamentoConsultaDTO dados);
}
