package dev.java10x.cadastrodefuncionarios.Funcionarios;
import dev.java10x.cadastrodefuncionarios.Tarefas.TarefaModel;
import dev.java10x.cadastrodefuncionarios.Tarefas.TarefaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/funcionarios/ui")
public class FuncionarioControllerUi {

    private final FuncionarioService funcionarioService;
    private final TarefaService tarefasService;

    public FuncionarioControllerUi(FuncionarioService funcionarioService, TarefaService tarefasService) {
        this.funcionarioService = funcionarioService;
        this.tarefasService = tarefasService;
    }

    @GetMapping("/listar")
    public String listarFuncionarios(Model model) {
        List<FuncionarioDTO> funcionarios = funcionarioService.listarFuncionarios();
        model.addAttribute("funcionarios", funcionarios);
        return "listarFuncionarios";
    }

    @GetMapping("/deletar/{id}")
    public String deletarFuncionarioPorId(@PathVariable Long id) {
        funcionarioService.deletarFuncionarioPorId(id);
        return "redirect:/funcionarios/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarFuncionariosPorId(@PathVariable Long id, Model model) {
        FuncionarioDTO funcionario = funcionarioService.listarFuncionariosPorId(id);

        if (funcionario != null) {
            model.addAttribute("funcionario", funcionario);
            return "detalhesFuncionario";
        } else {
            model.addAttribute("mensagem", "Funcionario não encontrado");
            return "listarFuncionarios";
        }
    }
    @PostMapping("/criar")
    public String salvarFuncionario(@ModelAttribute FuncionarioDTO funcionario,
                              RedirectAttributes redirectAttributes) {

        funcionarioService.criarFuncionario(funcionario);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionario criado com sucesso!");

        return "redirect:/funcionarios/ui/listar";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarFuncionario(Model model) {

        FuncionarioDTO funcionario = new FuncionarioDTO();
        funcionario.setTarefa(new TarefaModel());

        model.addAttribute("funcionario", funcionario);
        model.addAttribute("tarefas", tarefasService.listarTarefas());

        return "adicionarFuncionario";
    }
    @GetMapping("/editar/{id}")
    public String editarFuncionario(@PathVariable Long id, Model model) {
        FuncionarioDTO funcionario = funcionarioService.listarFuncionariosPorId(id);

        if (funcionario == null) {
            return "redirect:/funcionarios/ui/listar";
        }

        model.addAttribute("funcionario", funcionario);
        System.out.println("ID DO FUNCIONARIO: " + funcionario.getId());
        return "editarFuncionario"; // ou reutilizar adicionarNinja
    }



    @PostMapping("/atualizar/{id}")
    public String atualizarFuncionario(@PathVariable Long id, @ModelAttribute FuncionarioDTO funcionario, RedirectAttributes redirectAttributes) {

        funcionarioService.atualizarFuncionario(id, funcionario);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionario atualizado com sucesso!");
        return "redirect:/funcionarios/ui/listar";
    }



}