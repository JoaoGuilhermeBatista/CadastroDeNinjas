package dev.java10x.cadastrodeninja.Ninjas;

import dev.java10x.cadastrodeninja.Missoes.MissoesController;
import dev.java10x.cadastrodeninja.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Entity ele transforma uma classe em uma entitdade do BD
// JPA = Java Persistence API
@Entity
@Table(name = "tb_cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "idade")
    private int idade;

    @Column(name = "rank_ninja")
    private String rank_ninja;

    // @ManyToOne um ninja tem uma unica missao
    @ManyToOne
    @JoinColumn(name = "missoes_id") // Foreing Key
    private MissoesModel missoes;
}
