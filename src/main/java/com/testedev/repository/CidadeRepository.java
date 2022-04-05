package com.testedev.repository;

import com.testedev.models.Cidade;
import com.testedev.models.CidadeDTO;
import com.testedev.models.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CidadeRepository implements PanacheRepositoryBase<Cidade, Integer> {

    public List<CidadeDTO> findByEstadoResidente(String estadoResidente) {
        List<CidadeDTO> listDTO = new ArrayList<>();
        for (Cidade cidade : findAll().list()) {
            if (cidade.getEstadoResidente().getName().equalsIgnoreCase(estadoResidente)
                    || cidade.getEstadoResidente().getSigla().equalsIgnoreCase(estadoResidente)) {
                listDTO.add(new CidadeDTO(cidade));
            }
        }
        return listDTO;
    }

    public List<CidadeDTO> findByNome(String nomeCidade) {
        if (nomeCidade.length() < 3)
            return null;
        List<CidadeDTO> list = new ArrayList<>();
        for (Cidade cidade : findAll().list()) {
            boolean contagem = false;
            for (int i = 0; i < nomeCidade.length(); i++) {
                if(Character.toUpperCase(cidade.getNomeCidade().charAt(i))
                        != Character.toUpperCase(nomeCidade.charAt(i))){
                    contagem = false;
                    break;
                }
                contagem = true;
            }
            if(contagem)
                list.add(new CidadeDTO(cidade));
        }
        return list;
    }

    @Transactional
    public CidadeDTO updateCidade(Integer id, String nomeCidade, Integer idEstado){
        Cidade cidade = findById(id);
        if (nomeCidade != null) {
            cidade.setNomeCidade(nomeCidade);
        } else if (idEstado != null){
            cidade.setEstadoResidente(Estado.findById(idEstado));
        }
        persistAndFlush(cidade);
        return new CidadeDTO(cidade);
    }
}
