package com.testedev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CidadeDTO {

    public Integer id;
    public String nomeCidade;
    public int idEstado;

    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nomeCidade = cidade.getNomeCidade();
        this.idEstado = cidade.getEstadoResidente().getId();
    }

    public static List<CidadeDTO> convertList(List<Cidade> cidade){
        List<CidadeDTO> list = new ArrayList<>();
        for (Cidade cidades: cidade) {
            list.add(new CidadeDTO(cidades));
        }
        return list;
    }
}
