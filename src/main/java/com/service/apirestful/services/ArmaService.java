/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Arma;
import com.service.apirestful.model.Equipamiento;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.apirestful.repositories.ArmaRepository;
import com.service.apirestful.repositories.EquipamientoRepository;


@Service
public class ArmaService {

    @Autowired
    ArmaRepository repositoryArma;

    @Autowired
    EquipamientoRepository repositoryEquipamiento;

    public List<Arma> getAllArma() {
        List<Arma> armaList = repositoryArma.findAll();

        if (armaList.size() > 0) {
            return armaList;
        } else {
            return new ArrayList<Arma>();
        }
    }

    public Arma getArmaById(Long id) throws RecordNotFoundException {
        Optional<Arma> arma = repositoryArma.findById(id);

        if (arma.isPresent()) {
            return arma.get();
        } else {
            throw new RecordNotFoundException("No arma record exist for given id", id);
        }
    }

    public Arma createArma(Arma entity) {
        Equipamiento o = entity.getEquipamiento();
        if (o != null) {
            Equipamiento or = repositoryEquipamiento.findById(o.getId()).get();
            if (or != null) {
                entity.setEquipamiento(or);
            }
        }
        entity = repositoryArma.save(entity);
        return entity;
    }

    public Arma updateArma(Arma entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Arma> arma = repositoryArma.findById(entity.getId());

            if (arma.isPresent()) {
                Arma newEntity = arma.get();
                //newEntity.setId(entity.getId());
                newEntity.setTotalPrice(entity.getTotalPrice());
                newEntity.setRareza(entity.getrareza());

                Equipamiento o = entity.getEquipamiento();
                if (o != null) {
                    Equipamiento or = repositoryEquipamiento.findById(o.getId()).get();
                    if (or != null) {
                        o = or;
                    }
                    newEntity.setEquipamiento(o);
                }
                

                newEntity = repositoryArma.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Arma not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of arma given", 0l);
        }
    }

    public void deleteArmaById(Long id) throws RecordNotFoundException {
        Optional<Arma> arma = repositoryArma.findById(id);

        if (arma.isPresent()) {
            repositoryArma.deleteById(id);
        } else {
            throw new RecordNotFoundException("No bill record exist for given id", id);
        }
    }

    public List<Arma> getArmaByCode(String code) {
        List<Arma> armaList = repositoryArma.getByCode(code);

        if (armaList.size() > 0) {
            return armaList;
        } else {
            return new ArrayList<Arma>();
        }
    }

}
