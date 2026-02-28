package dev.java10x.cadastrodefuncionarios.Funcionarios;

import dev.java10x.cadastrodefuncionarios.Tarefas.TarefaModel;
import dev.java10x.cadastrodefuncionarios.Tarefas.TarefaRepository;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {

    private final TarefaRepository tarefaRepository;

    public FuncionarioMapper(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public FuncionarioModel map(FuncionarioDTO dto) {

        FuncionarioModel model = new FuncionarioModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());
        model.setIdade(dto.getIdade());
        model.setCargo(dto.getCargo());

        if (dto.getTarefa() != null && dto.getTarefa().getId() != null) {
            TarefaModel tarefa = tarefaRepository
                    .findById(dto.getTarefa().getId())
                    .orElse(null);
            model.setTarefa(tarefa);
        }

        return model;
    }

    public FuncionarioDTO map(FuncionarioModel model) {

        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setEmail(model.getEmail());
        dto.setIdade(model.getIdade());
        dto.setCargo(model.getCargo());
        dto.setTarefa(model.getTarefa());

        return dto;
    }
}