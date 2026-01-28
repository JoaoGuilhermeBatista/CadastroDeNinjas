package dev.java10x.cadastrodeninja.Missoes;

import dev.java10x.cadastrodeninja.Ninjas.NinjaDTO;
import dev.java10x.cadastrodeninja.Ninjas.NinjaMapper;
import dev.java10x.cadastrodeninja.Ninjas.NinjaModel;
import dev.java10x.cadastrodeninja.Ninjas.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }


    public List<MissoesDTO> listarMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    public MissoesDTO criarMissoes(MissoesDTO missoesDTO) {
        MissoesModel missao = missoesMapper.map(missoesDTO);
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao);
    }

    public MissoesDTO atualizarMissoes(Long id,  MissoesDTO missoesDTO) {
        Optional<MissoesModel> missoesModel = missoesRepository.findById(id);
        if (missoesModel.isPresent()) {
            MissoesModel atualizarMissoes = missoesMapper.map(missoesDTO);
            atualizarMissoes.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(atualizarMissoes);
            return missoesMapper.map(missaoSalva);
        }


        return null;
    }

    public MissoesDTO listarMissoesPorId(Long id) {
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missoesMapper::map).orElse(null);
    }

    public void deletarMissaoPorId(Long id) {
        missoesRepository.deleteById(id);
    }

}
