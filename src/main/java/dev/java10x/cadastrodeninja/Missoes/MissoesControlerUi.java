package dev.java10x.cadastrodeninja.Missoes;

import dev.java10x.cadastrodeninja.Ninjas.NinjaDTO;
import dev.java10x.cadastrodeninja.Ninjas.NinjaSerivce;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControlerUi {

    private final MissoesService missoesService;
    private final NinjaSerivce ninjaSerivce;

    public MissoesControlerUi(MissoesService missoesService, NinjaSerivce ninjaSerivce) {
        this.missoesService = missoesService;
        this.ninjaSerivce = ninjaSerivce;
    }

    @GetMapping("/listar")
    public String listarMissoes (Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissoes(@PathVariable Long id) {
        missoesService.deletarMissaoPorId(id);
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarMissoesPorId(@PathVariable Long id, Model model) {
        MissoesDTO missoes = missoesService.listarMissoesPorId(id);

        if (missoes != null) {
            model.addAttribute("missoes", missoes);
            return "detalhesninja";
        } else {
            model.addAttribute("mensagem", "Missão não encontrada");
            return "listarMissoes";
        }
    }
    @PostMapping("/criar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja) {
        ninjaSerivce.criarNinja(ninja);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/adicionar")
    public String mostarFormularioAdicionarMissoes(Model model) {
        model.addAttribute("missoes", new MissoesDTO());
        return "adicionarMissoes";
    }

    @GetMapping("/editar/{id}")
    public String editarMissoes(@PathVariable Long id, Model model) {
        MissoesDTO missoes = missoesService.listarMissoesPorId(id);

        if (missoes == null) {
            return "redirect:/missoes/ui/listar";
        }

        model.addAttribute("missoes", missoes);
        return "editarMissoes";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarMissoes(@PathVariable Long id, @ModelAttribute MissoesDTO missoes, RedirectAttributes redirectAttributes) {

        missoesService.atualizarMissoes(id, missoes);
        redirectAttributes.addFlashAttribute("mensagem", "Missoes atualizado");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesMissao(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarMissoesPorId(id);

        if (missao != null) {
            model.addAttribute("missao", missao);
            return "detalhesMissao";
        } else {
            return "redirect:/missoes/ui/listar";
        }
    }
}
