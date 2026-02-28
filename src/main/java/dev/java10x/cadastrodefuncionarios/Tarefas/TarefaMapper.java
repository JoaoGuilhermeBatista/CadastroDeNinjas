package dev.java10x.cadastrodefuncionarios.Tarefas;

import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

    public TarefaModel map (TarefaDTO tarefaDTO) {
        TarefaModel tarefaModel = new TarefaModel();
        tarefaModel.setId(tarefaDTO.getId());
        tarefaModel.setNome(tarefaDTO.getNome());
        tarefaModel.setPrioridade(tarefaDTO.getPrioridade());

        return tarefaModel;
    }

    public TarefaDTO map(TarefaModel tarefaModel) {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setId(tarefaModel.getId());
        tarefaDTO.setNome(tarefaModel.getNome());
        tarefaDTO.setPrioridade(tarefaModel.getPrioridade());
        return tarefaDTO;
    }


}

