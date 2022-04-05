package com.testedev.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "cidade")
@NoArgsConstructor
public class Cidade extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome_cidade")
    @NotEmpty
    private String nomeCidade;
    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_estado")
    private Estado estadoResidente;

    public Cidade (CidadeDTO cidadeDTO){
        this.nomeCidade = cidadeDTO.nomeCidade;
        this.estadoResidente = new Estado();
        this.estadoResidente.setId(cidadeDTO.getIdEstado());
    }
}
