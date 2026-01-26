package dev.java10x.cadastrodeninja.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private NinjaSerivce ninjaSerivce;

    public NinjaController(NinjaSerivce ninjaSerivce) {
        this.ninjaSerivce = ninjaSerivce;
    }

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }
    // Adicionar Ninja (Create)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaSerivce.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja Criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Mostrar todos os ninjas (Read)
    @GetMapping("/listar")
        public ResponseEntity<List<NinjaDTO>> listarNinjas (){
        List<NinjaDTO> ninjas = ninjaSerivce.listarNinjas() ;
        return ResponseEntity.ok(ninjas);
    }

    // Mostrar Ninja por ID (Read)
    @GetMapping("/listar/{id}")
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
    public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {
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
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
        if (ninjaSerivce.listarNinjasPorId(id) != null) {
            ninjaSerivce.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o ID " + id + " não foi encontrado ");
        }


    }

}
