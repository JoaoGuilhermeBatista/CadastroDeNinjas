package dev.java10x.cadastrodeninja.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private final NinjaSerivce ninjaSerivce;

    public NinjaController(NinjaSerivce ninjaSerivce) {
        this.ninjaSerivce = ninjaSerivce;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }
    // Adicionar Ninja (Create)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja é insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criacao do ninja")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaSerivce.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja Criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Mostrar todos os ninjas (Read)
    @GetMapping("/listar")
    @Operation(summary = "Lista os ninjas", description = "Lista todos os ninjas presentes no banco de dados")
        public ResponseEntity<List<NinjaDTO>> listarNinjas (){
        List<NinjaDTO> ninjas = ninjaSerivce.listarNinjas() ;
        return ResponseEntity.ok(ninjas);
    }

    // Mostrar Ninja por ID (Read)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista um ninja por id", description = "Rota lista um ninja para o ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaSerivce.listarNinjasPorId(id);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND))
                    .body("Ninja com o ID " + id + " não existe nos nossos registros ");
        }
    }



    // Alterar dados dos ninjas (Update)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera um ninja por Id", description = "Rota altera um ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não encontrado, não foi possivel alterar")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "Usuario manda o Id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisição")
            @RequestBody NinjaDTO ninjaAtualizado) {
        NinjaDTO ninja = ninjaSerivce.atualizarNinja(id,ninjaAtualizado);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND))
                    .body("Ninja com o ID " + id + (" não existe nos nossos registros"));
        }
    }


    // Deletar Ninja (Delete)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o ninja pelo Id", description = "Deleta o ninja pelo seu Id caso esteja presente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não existe")
    })
    public ResponseEntity<String> deletarNinjaPorId(
            @Parameter(description = "Usuario manda o Id no caminho da requisição")
            @PathVariable Long id){
        if (ninjaSerivce.listarNinjasPorId(id) != null) {
            ninjaSerivce.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o ID " + id + " não foi encontrado ");
        }


    }

}
