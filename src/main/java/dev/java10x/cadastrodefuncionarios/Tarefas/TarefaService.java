package dev.java10x.cadastrodefuncionarios.Tarefas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public TarefaService(TarefaRepository tarefaRepository, TarefaMapper tarefaMapper) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
    }

    public List<TarefaDTO> listarTarefas() {
        List<TarefaModel> tarefas = tarefaRepository.findAll();
        return tarefas.stream()
                .map(tarefaMapper::map)
                .collect(Collectors.toList());
    }

    public TarefaDTO criarTarefas(TarefaDTO tarefaDTO) {
        TarefaModel tarefa = tarefaMapper.map(tarefaDTO);
        tarefa = tarefaRepository.save(tarefa);
        return tarefaMapper.map(tarefa);
    }

    public TarefaDTO atualizarTarefas(Long id, TarefaDTO tarefaDTO) {
        Optional<TarefaModel> tarefaModel = tarefaRepository.findById(id);
        if (tarefaModel.isPresent()) {
            TarefaModel atualizarTarefas = tarefaMapper.map(tarefaDTO);
            atualizarTarefas.setId(id);
            TarefaModel tarefaSalva = tarefaRepository.save(atualizarTarefas);
            return tarefaMapper.map(tarefaSalva);
        }


        return null;
    }

    public TarefaDTO listarTarefasPorId(Long id) {
        Optional<TarefaModel> tarefasPorId = tarefaRepository.findById(id);
        return tarefasPorId.map(tarefaMapper::map).orElse(null);
    }

    public void deletarTarefaPorId(Long id) {
        tarefaRepository.deleteById(id);
    }

}