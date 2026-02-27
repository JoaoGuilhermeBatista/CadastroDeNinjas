package dev.java10x.cadastrodeninja.Ninjas;

import dev.java10x.cadastrodeninja.Missoes.MissoesModel;
import dev.java10x.cadastrodeninja.Missoes.MissoesRepository;
import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    private final MissoesRepository missoesRepository;

    public NinjaMapper(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // DTO -> ENTITY
    public NinjaModel map(NinjaDTO ninjaDTO) {

        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(ninjaDTO.getId());
        ninjaModel.setNome(ninjaDTO.getNome());
        ninjaModel.setEmail(ninjaDTO.getEmail());
        ninjaModel.setIdade(ninjaDTO.getIdade());
        ninjaModel.setImgUrl(ninjaDTO.getImgUrl());
        ninjaModel.setRank_ninja(ninjaDTO.getRank_ninja());

        // 🔥 RELACIONAMENTO MISSÃO
        if (ninjaDTO.getMissoes() != null &&
                ninjaDTO.getMissoes().getId() != null) {

            MissoesModel missao = missoesRepository
                    .findById(ninjaDTO.getMissoes().getId())
                    .orElse(null);

            ninjaModel.setMissoes(missao);
        }

        return ninjaModel;
    }

    // ENTITY -> DTO
    public NinjaDTO map(NinjaModel ninjaModel) {

        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setNome(ninjaModel.getNome());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setIdade(ninjaModel.getIdade());
        ninjaDTO.setImgUrl(ninjaModel.getImgUrl());
        ninjaDTO.setRank_ninja(ninjaModel.getRank_ninja());

        // 🔥 MANDAR MISSÃO PARA O FRONT
        ninjaDTO.setMissoes(ninjaModel.getMissoes());

        return ninjaDTO;
    }
}