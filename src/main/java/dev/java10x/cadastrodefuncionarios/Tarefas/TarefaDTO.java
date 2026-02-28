package dev.java10x.cadastrodefuncionarios.Tarefas;

import dev.java10x.cadastrodefuncionarios.Funcionarios.FuncionarioModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {
    private Long id;
    private String nome;
    private String prioridade;
    private List<FuncionarioModel> funcionarios;
}
