package com.testedev.service;

import com.testedev.models.Cidade;
import com.testedev.models.CidadeDTO;
import com.testedev.models.Estado;
import com.testedev.repository.CidadeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
public class CidadeService {

    @Inject
    CidadeRepository repository;

    public CidadeDTO getById(int id) {
        CidadeDTO cidade = new CidadeDTO(repository.findById(id));
        return cidade;
    }

    public List<CidadeDTO> getByEstado(String estado) {
        return repository.findByEstadoResidente(estado);
    }

    public List<CidadeDTO> getByNome(String nome) {
        return repository.findByNome(nome);
    }

    public long getNumRegistros() {
        return repository.count();
    }

    public List<CidadeDTO> getCidade(int page, int size) {
        return CidadeDTO.convertList(repository.findAll()
                .page(Math.max(page, 0)
                        , size <= 0 ? 24 : size)
                .list());
    }

    @Transactional
    public CidadeDTO createCidade(CidadeDTO cidade) {
        Cidade cidadePersist = new Cidade(cidade);
        cidadePersist.setEstadoResidente(Estado.findById(cidade.getIdEstado()));
        repository.persistAndFlush(cidadePersist);
        return cidade;
    }

    public CidadeDTO updateCidade(@Valid CidadeDTO cidadeDTO) {
        return repository.updateCidade(cidadeDTO.getId(),
                cidadeDTO.getNomeCidade(),
                cidadeDTO.getIdEstado());
    }

    @Transactional
    public void deleteCidade(Integer id) {
        repository.deleteById(id);
    }
}