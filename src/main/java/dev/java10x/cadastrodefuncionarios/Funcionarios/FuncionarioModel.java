package dev.java10x.cadastrodefuncionarios.Funcionarios;
import dev.java10x.cadastrodefuncionarios.Tarefas.TarefaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Entity ele transforma uma classe em uma entitdade do BD
// JPA = Java Persistence API
@Entity
@Table(name = "tb_funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tarefa")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private int idade;

    @Column(name = "cargo")
    private String cargo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tarefa_id")
    private TarefaModel tarefa;
}