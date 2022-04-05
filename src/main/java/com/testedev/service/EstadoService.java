package com.testedev.service;

import com.testedev.models.Estado;
import com.testedev.repository.EstadoRepository;
import io.quarkus.panache.common.Page;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
public class EstadoService {

    @Inject
    @Autowired
    EstadoRepository repository;

    public List<Estado> getEstados(int size, int page) {
        return repository.findAll()
                .page(Page.of(
                        Math.max(page, 0),
                        size <= 0 ? 24 : size))
                .list();
    }

    public List<Estado> getEstados() {
        return repository.findAll().list();
    }

    public long getNumRegistros() {
        return repository.count();
    }

    @Transactional
    public Estado createEstado(@Valid Estado estado) {
        repository.persistAndFlush(estado);
        return estado;
    }

    public Estado updateEstado(Estado estado) {
        return repository.updateEstado(estado);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}









