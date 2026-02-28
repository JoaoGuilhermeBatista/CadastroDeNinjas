package dev.java10x.cadastrodefuncionarios.Funcionarios;

import dev.java10x.cadastrodefuncionarios.Tarefas.TarefaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

    private Long id;
    private String nome;
    private String email;
    private int idade;
    private String cargo;
    private TarefaModel tarefa;
}