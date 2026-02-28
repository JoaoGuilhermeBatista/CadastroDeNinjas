package dev.java10x.cadastrodefuncionarios.Tarefas;

import dev.java10x.cadastrodefuncionarios.Funcionarios.FuncionarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tarefas/ui")
public class TarefaControllerUi {

    private final TarefaService tarefaService;
    private final FuncionarioService funcionarioService;

    public TarefaControllerUi(TarefaService tarefaService, FuncionarioService funcionarioService) {
        this.tarefaService = tarefaService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/listar")
    public String listarTarefas (Model model) {
        List<TarefaDTO> tarefas = tarefaService.listarTarefas();
        model.addAttribute("tarefas", tarefas);
        return "listarTarefas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarTarefas(@PathVariable Long id) {
        tarefaService.deletarTarefaPorId(id);
        return "redirect:/tarefas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarTarefasPorId(@PathVariable Long id, Model model) {
        TarefaDTO tarefas = tarefaService.listarTarefasPorId(id);

        if (tarefas != null) {
            model.addAttribute("tarefas", tarefas);
            return "detalhesfuncionarios";
        } else {
            model.addAttribute("mensagem", "Tarefa não encontrada");
            return "listarTarefas";
        }
    }
    @PostMapping("/criar")
    public String salvarTarefa(@ModelAttribute TarefaDTO tarefa,
                               RedirectAttributes redirectAttributes) {
        tarefaService.criarTarefas(tarefa);
        redirectAttributes.addFlashAttribute("mensagem", "Tarefa criada com sucesso");
        return "redirect:/tarefas/ui/listar";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarTarefas(Model model) {
        model.addAttribute("tarefas", new TarefaDTO());
        return "adicionarTarefa";
    }

    @GetMapping("/editar/{id}")
    public String editarTarefas(@PathVariable Long id, Model model) {
        TarefaDTO tarefas = tarefaService.listarTarefasPorId(id);

        if (tarefas == null) {
            return "redirect:/tarefas/ui/listar";
        }

        model.addAttribute("tarefas", tarefas);
        return "editarTarefas";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarTarefas(@PathVariable Long id, @ModelAttribute TarefaDTO tarefas, RedirectAttributes redirectAttributes) {

        tarefaService.atualizarTarefas(id, tarefas);
        redirectAttributes.addFlashAttribute("mensagem", "Tarefas atualizadas");
        return "redirect:/tarefas/ui/listar";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesTarefas(@PathVariable Long id, Model model) {
        TarefaDTO tarefa = tarefaService.listarTarefasPorId(id);

        if (tarefa != null) {
            model.addAttribute("tarefa", tarefa);
            return "detalhesTarefa";
        } else {
            return "redirect:/tarefas/ui/listar";
        }
    }
}
