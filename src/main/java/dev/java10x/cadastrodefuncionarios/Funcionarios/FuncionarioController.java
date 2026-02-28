package dev.java10x.cadastrodefuncionarios.Funcionarios;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioSerivce;

    public FuncionarioController(FuncionarioService funcionarioSerivce) {
        this.funcionarioSerivce = funcionarioSerivce;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }
    // Adicionar Ninja (Create)
    @PostMapping("/criar")
    @Operation(summary = "Introduz um novo funcionário no sistema", description = "Rota cria um novo funcionario é insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionario inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na inserção do funcionario")
    })
    public ResponseEntity<String> criarFuncionario(@RequestBody FuncionarioDTO funcionario) {
        FuncionarioDTO novoFuncionario = funcionarioSerivce.criarFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Funcionario Criado com sucesso: " + novoFuncionario.getNome() + " (ID): " + novoFuncionario.getId());
    }

    // Mostrar todos os ninjas (Read)
    @GetMapping("/listar")
    @Operation(summary = "Lista os funcionarios", description = "Lista todos os funcionarios presentes no banco de dados")
    public ResponseEntity<List<FuncionarioDTO>> listarFuncionarios (){
        List<FuncionarioDTO> funcionarios = funcionarioSerivce.listarFuncionarios() ;
        return ResponseEntity.ok(funcionarios);
    }

    // Mostrar Ninja por ID (Read)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista um funcionario por id", description = "Rota lista um funcionario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionario encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionario não encontrado")
    })
    public ResponseEntity<?> listarFuncionariosPorId(@PathVariable Long id) {
        FuncionarioDTO funcionario = funcionarioSerivce.listarFuncionariosPorId(id);

        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND))
                    .body("Funcionario com o ID " + id + " não existe nos nossos registros ");
        }
    }



    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera os dados do funcionario por Id", description = "Rota altera um funcionario pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionario alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Funcionario não encontrado, não foi possivel alterar")
    })
    public ResponseEntity<?> alterarFuncionarioPorId(
            @Parameter(description = "Usuario manda o Id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do funcionario a ser atualizado no corpo da requisição")
            @RequestBody FuncionarioDTO funcionarioAtualizado) {
        FuncionarioDTO funcionario = funcionarioSerivce.atualizarFuncionario(id,funcionarioAtualizado);

        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND))
                    .body("Funcionario com o ID " + id + (" não existe nos nossos registros"));
        }
    }


    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o funcionario pelo Id", description = "Deleta o funcionario pelo seu Id caso esteja presente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionario deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Funcionario não existe")
    })
    public ResponseEntity<String> deletarFuncionarioPorId(
            @Parameter(description = "Usuario manda o Id no caminho da requisição")
            @PathVariable Long id){
        if (funcionarioSerivce.listarFuncionariosPorId(id) != null) {
            funcionarioSerivce.deletarFuncionarioPorId(id);
            return ResponseEntity.ok("Funcionario com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O funcionario com o ID " + id + " não foi encontrado ");
        }


    }

}