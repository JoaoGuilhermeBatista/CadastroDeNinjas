package dev.java10x.cadastrodeninja.Missoes;
import dev.java10x.cadastrodeninja.Ninjas.NinjaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {


    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping ("/listar")
    @Operation(summary = "Lista as missões", description = "Lista as missões presentes no banco de dados")
    public ResponseEntity<List<MissoesDTO>> listaMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista uma missão por id", description = "Rota lista a missão por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<?> listarMissoesPorId(@PathVariable Long id) {
        MissoesDTO missao = missoesService.listarMissoesPorId(id);

        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND))
                    .body("Missão com o ID " + id + " não existe nos nossos registros ");
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria as missões", description = "Usuario insere a missão a ser efetuada")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoes){
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada: " +  novaMissao.getNome() + " (ID)" + novaMissao.getId() + " dificudade: " + novaMissao.getDificuldade());
    }

    @PutMapping ("/alterar/{id}")
    @Operation(summary = "Altera os dados da missão por Id", description = "Rota altera os dados da missão pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missao não encontrado/Concluida, não foi possivel alterar")
    })
    public ResponseEntity<?> alterarMissaoPorId(
            @Parameter(description = "Usuario manda o Id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados da missao a ser alterada")
            @RequestBody MissoesDTO missoesAtualizadas) {
        MissoesDTO missao = missoesService.atualizarMissoes(id, missoesAtualizadas);

        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao com o ID " + id + " não existe no nosso banco de dados");
        }
    }


    @DeleteMapping ("/deletar/{id}")
    @Operation(summary = "Deleta a missao por Id", description = "Deleta a missao pelo seu Id caso esteja presente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "missao deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "missao não existe")
    })
    public ResponseEntity<String> deletarMissaoPorId(
            @Parameter(description = "Usuario manda o Id no caminho da requisição" )
            @PathVariable Long id) {
        if (missoesService.listarMissoesPorId(id) != null) {
            missoesService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missao com o id " + id + " deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão com o id: " + id + " não foi encontrado no nosso banco de dados");
        }
    }

}