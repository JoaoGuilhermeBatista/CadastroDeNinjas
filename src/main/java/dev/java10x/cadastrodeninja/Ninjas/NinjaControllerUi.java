package dev.java10x.cadastrodeninja.Ninjas;
import dev.java10x.cadastrodeninja.Missoes.MissoesModel;
import dev.java10x.cadastrodeninja.Missoes.MissoesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaSerivce ninjaService;
    private final MissoesService missoesService;

    public NinjaControllerUi(NinjaSerivce ninjaService,
                             MissoesService missoesService) {

        this.ninjaService = ninjaService;
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detalhesNinja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarNinjas";
        }
    }
    @PostMapping("/criar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja,
                              RedirectAttributes redirectAttributes) {

        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja criado com sucesso!");

        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {

        NinjaDTO ninja = new NinjaDTO();
        ninja.setMissoes(new MissoesModel());

        model.addAttribute("ninja", ninja);
        model.addAttribute("missoes", missoesService.listarMissoes());

        return "adicionarNinja";
    }
    @GetMapping("/editar/{id}")
    public String editarNinja(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja == null) {
            return "redirect:/ninjas/ui/listar";
        }

        model.addAttribute("ninja", ninja);
        return "editarNinja"; // ou reutilizar adicionarNinja
    }



    @PostMapping("/atualizar/{id}")
    public String atualizarNinja(@PathVariable Long id, @ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {

        ninjaService.atualizarNinja(id, ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja atualizado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }



}