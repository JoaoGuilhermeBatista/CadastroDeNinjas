package dev.java10x.cadastrodefuncionarios.Tarefas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefas")
public class TarefaController {


    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping ("/listar")
    @Operation(summary = "Lista as tarefas que o funcionario deve fazer", description = "Lista as tarefas pendentes no banco de dados")
    public ResponseEntity<List<TarefaDTO>> listaTarefas(){
        List<TarefaDTO> tarefas = tarefaService.listarTarefas();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista uma tarefa por id", description = "Rota lista a tarefa por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<?> listarTarefasPorId(@PathVariable Long id) {
        TarefaDTO tarefa = tarefaService.listarTarefasPorId(id);

        if (tarefa != null) {
            return ResponseEntity.ok(tarefa);
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND))
                    .body("Tarefa com o ID " + id + " não existe nos nossos registros/foi realizada ");
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria as tarefas", description = "Usuario insere a tarefa a ser efetuada")
    public ResponseEntity<String> criarTarefa(@RequestBody TarefaDTO tarefa){
        TarefaDTO novaTarefa = tarefaService.criarTarefas(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada: " +  novaTarefa.getNome() + " (ID)" + novaTarefa.getId() + " dificudade: " + novaTarefa.getPrioridade());
    }

    @PutMapping ("/alterar/{id}")
    @Operation(summary = "Altera os dados da tarefa por Id", description = "Rota altera os dados da tarefa pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Tarefa não encontrado/Concluida, não foi possivel alterar")
    })
    public ResponseEntity<?> alterarTarefaPorId(
            @Parameter(description = "Usuario manda o Id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados da tarefa a ser alterada")
            @RequestBody TarefaDTO tarefasAtualizadas) {
        TarefaDTO tarefa = tarefaService.atualizarTarefas(id, tarefasAtualizadas);

        if (tarefa != null) {
            return ResponseEntity.ok(tarefa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa com o ID " + id + " não existe no nosso banco de dados/foi realizada");
        }
    }


    @DeleteMapping ("/deletar/{id}")
    @Operation(summary = "Deleta a tarefa por Id", description = "Deleta a tarefa pelo seu Id caso esteja presente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "tarefa não existe")
    })
    public ResponseEntity<String> deletarTarefaPorId(
            @Parameter(description = "Usuario manda o Id no caminho da requisição" )
            @PathVariable Long id) {
        if (tarefaService.listarTarefasPorId(id) != null) {
            tarefaService.deletarTarefaPorId(id);
            return ResponseEntity.ok("Tarefa com o id " + id + " deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A tarefa com o id: " + id + " não foi encontrado no nosso banco de dados");
        }
    }

}