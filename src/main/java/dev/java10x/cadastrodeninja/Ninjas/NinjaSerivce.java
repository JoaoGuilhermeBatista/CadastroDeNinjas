package dev.java10x.cadastrodeninja.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
