package med.voll.api.domain.repository;

import med.voll.api.domain.model.Especialidade;
import med.voll.api.domain.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT m FROM Medico m
            WHERE
            m.ativo = true
            AND
            m.especialidade = :especialidade
            AND
            m.id not in(
                SELECT c.medico.id FROM Consulta c
                WHERE
                c.data = :data
            )
            order by rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.ativo
            FROM Medico m
            WHERE 
            m.id = :idMedico
            """)
    boolean findAtivoById(Long idMedico);
}
