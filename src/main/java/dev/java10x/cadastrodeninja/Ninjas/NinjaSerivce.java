package dev.java10x.cadastrodeninja.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaSerivce {

    private NinjaRepository ninjaRepository;

    public NinjaSerivce(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    // Listar todos os meus ninjas
    public List<NinjaModel> listarNinjas() {
    return ninjaRepository.findAll();
    }

    // Listar todos os meus ninjas por ID
    public NinjaModel listarNinjasPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.orElse(null);
    }

    // Criar um novo ninja
    public NinjaModel criarNinja (NinjaModel ninja) {
        return  ninjaRepository.save(ninja);
    }

    // Deletar o ninja - Tem que ser um metodo VOID
    public void deletarNinjaPorId(Long id) {
        ninjaRepository.deleteById(id);
    }

    // Atualizar ninja
    public NinjaModel atualizarNinja(Long Id, NinjaModel ninjaAtualizado) {
        if (ninjaRepository.existsById(Id)) {
            ninjaAtualizado.setId(Id);
            return ninjaRepository.save(ninjaAtualizado);
        }
        return null;
    }
}
