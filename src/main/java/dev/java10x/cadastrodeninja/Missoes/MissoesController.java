package dev.java10x.cadastrodeninja.Missoes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {



    @GetMapping ("/listar")
    public String listarMissao() {
        return "Missoes listadas com sucesso";
    }
    @PostMapping("/criar")
    public String criarMissao() {
    return "Missao criada com sucesso";
}


@PutMapping ("/alterar")
    public String alterarMissao() {
    return "Missao alterada com sucesso";
}

@DeleteMapping ("/deletar")
    public String deletarMissao() {
    return "Missao deletada com sucesso";
}


}
