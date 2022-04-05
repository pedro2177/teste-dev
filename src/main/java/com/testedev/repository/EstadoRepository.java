package com.testedev.repository;

import com.testedev.models.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class EstadoRepository implements PanacheRepositoryBase<Estado, Integer> {

    @Transactional
    public Estado updateEstado(Estado estado){
        Estado estadoPesist = findById(estado.getId());
//        if (estado.getName() != null) {
            estadoPesist.setName(estadoPesist.getName());
//        } else if (estado.getSigla() != null){
            estadoPesist.setSigla(estado.getSigla());
//        }
        persistAndFlush(estadoPesist);
        return estadoPesist;
    }
}
