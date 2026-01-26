package dev.java10x.cadastrodeninja.Ninjas;

import dev.java10x.cadastrodeninja.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaDTO {
    private long id;
    private String nome;
    private String email;
    private String imgUrl;
    private int idade;
    private String rank_ninja;
    private MissoesModel missoes;
}
