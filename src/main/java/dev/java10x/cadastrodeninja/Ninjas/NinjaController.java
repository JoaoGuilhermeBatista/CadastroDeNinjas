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
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja) {
        return ninjaSerivce.criarNinja(ninja);
    }

    // Mostrar todos os ninjas (Read)
    @GetMapping("/listar")
        public List<NinjaDTO> listarNinjas (){
        return ninjaSerivce.listarNinjas() ;
    }

    // Mostrar Ninja por ID (Read)
    @GetMapping("/listar/{id}")
    public NinjaDTO listarNinjasPorId(@PathVariable Long id) {
        return ninjaSerivce.listarNinjasPorId(id);
    }



    // Alterar dados dos ninjas (Update)
    @PutMapping("/alterar/{id}")
    public NinjaDTO alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {
        return ninjaSerivce.atualizarNinja(id,ninjaAtualizado);
    }


    // Deletar Ninja (Delete)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorId(@PathVariable Long id){
        ninjaSerivce.deletarNinjaPorId(id);
    }

}
