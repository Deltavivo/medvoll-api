package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.DadosAtualizarMedicoDTO;
import med.voll.api.domain.dto.DadosCadastroMedicoDTO;
import med.voll.api.domain.dto.DadosDetalhamentoMedicoDTO;
import med.voll.api.domain.dto.DadosListagemMedicoDTO;
import med.voll.api.domain.model.Medico;
import med.voll.api.domain.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


//Validar por perfil para os metodos é necessario incluir anotacao na classe tambem
//@EnableMethodSecurity(securedEnabled = true)

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(new Medico(dados));
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
    }

    // Metodo sem paginacao
//    @GetMapping
//    public List<DadosListagemMedicoDTO> listar(){
//        return repository.findAll().stream().map(DadosListagemMedicoDTO::new).toList();
//    }

    // Metodo com paginacao para devolucao ao frontend
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listar(@PageableDefault(size=10, page=0, sort={"nome"}) Pageable paginacao){

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new);
        return ResponseEntity.ok(page);
        // na url eh possivel passar por parametro ?size=1&page=1&sort=nome ou sort=crm,desc ou asc
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedicoDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

    //Restricao por perfil do metodo por anotacao
    //@Secured("ROLE_ADMIN")

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@RequestParam Long id){
       // repository.deleteById(id);

        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@RequestParam Long id){

        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }
}
