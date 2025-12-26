package dev.java10x.cadastrodeninja.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }
    // Adicionar Ninja (Create)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    // Mostrar todos os ninjas (Read)
    @GetMapping("/todos")
        public String mostraTodosOsNinjas (){
        return "Mostrar Ninja por id";
    }

    // Mostrar Ninja por ID (Read)
    @GetMapping("/todosID")
    public String mostraTodosOsNinjasPorID (){
        return "Mostrar Ninja por id";
    }


    // Alterar dados dos ninjas (Update)
    @PutMapping("/alterarID")
    public String alterarID(){
        return "Alterar Ninja por id";
    }


    // Deletar Ninja (Delete)
    @DeleteMapping("/deletarID")
    public String deletarID(){
        return "Deletar Ninja por id";
    }

}
