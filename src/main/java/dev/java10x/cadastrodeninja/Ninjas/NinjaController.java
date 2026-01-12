package dev.java10x.cadastrodeninja.Ninjas;

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
    public NinjaModel criarNinja(@RequestBody NinjaModel ninja) {
        return ninjaSerivce.criarNinja(ninja);
    }

    // Mostrar todos os ninjas (Read)
    @GetMapping("/listar")
        public List<NinjaModel> listarNinjas (){
        return ninjaSerivce.listarNinjas() ;
    }

    // Mostrar Ninja por ID (Read)
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasPorId(@PathVariable Long id) {
        return ninjaSerivce.listarNinjasPorId(id);
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
