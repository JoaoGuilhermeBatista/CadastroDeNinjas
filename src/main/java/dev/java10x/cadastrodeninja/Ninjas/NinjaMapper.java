package dev.java10x.cadastrodeninja.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    public NinjaModel map (NinjaDTO ninjaDTO) {
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(ninjaDTO.getId());
        ninjaModel.setNome(ninjaDTO.getNome());
        ninjaModel.setEmail(ninjaDTO.getEmail());
        ninjaModel.setIdade(ninjaDTO.getIdade());
        ninjaModel.setImgUrl(ninjaDTO.getImgUrl());
        ninjaModel.setRank_ninja(ninjaDTO.getRank_ninja());
        ninjaModel.setMissoes(ninjaDTO.getMissoes());

        return ninjaModel;
    }

    public NinjaDTO map(NinjaModel ninjaModel) {
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setNome(ninjaModel.getNome());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setIdade(ninjaModel.getIdade());
        ninjaDTO.setImgUrl(ninjaModel.getImgUrl());
        ninjaDTO.setRank_ninja(ninjaModel.getRank_ninja());
        ninjaDTO.setMissoes(ninjaModel.getMissoes());
        return ninjaDTO;

    }


}
