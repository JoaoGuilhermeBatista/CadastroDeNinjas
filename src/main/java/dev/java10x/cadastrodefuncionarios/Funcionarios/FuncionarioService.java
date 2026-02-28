package dev.java10x.cadastrodefuncionarios.Funcionarios;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    // Listar todos os meus ninjas
    public List<FuncionarioDTO> listarFuncionarios() {
        List<FuncionarioModel> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(funcionarioMapper:: map)
                .collect(Collectors.toList());
    }

    // Listar todos os meus ninjas por ID
    public FuncionarioDTO listarFuncionariosPorId(Long id) {
        Optional<FuncionarioModel> funcionarioPorId = funcionarioRepository.findById(id);
        return funcionarioPorId.map(funcionarioMapper::map).orElse(null);
    }

    // Criar um novo ninja
    public FuncionarioDTO criarFuncionario (FuncionarioDTO funcionarioDTO) {
        FuncionarioModel funcionario = funcionarioMapper.map(funcionarioDTO);
        funcionario = funcionarioRepository.save(funcionario);
        return funcionarioMapper.map(funcionario);
    }

    // Deletar o ninja - Tem que ser um metodo VOID
    public void deletarFuncionarioPorId(Long id) {
        funcionarioRepository.deleteById(id);
    }

    // Atualizar ninja
    public FuncionarioDTO atualizarFuncionario(Long Id, FuncionarioDTO funcionarioDTO) {
        Optional<FuncionarioModel> funcionarioExistente = funcionarioRepository.findById(Id);
        if (funcionarioExistente.isPresent()) {
            FuncionarioModel funcionaroAtualizado = funcionarioMapper.map(funcionarioDTO);
            funcionaroAtualizado.setId(Id);
            FuncionarioModel funcionarioSalvo = funcionarioRepository.save(funcionaroAtualizado);
            return funcionarioMapper.map(funcionarioSalvo);
        }
        return null;
    }
}