/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Arma;
import com.service.apirestful.model.Personaje;
import com.service.apirestful.model.Equipamiento;
import com.service.apirestful.model.Item;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.apirestful.repositories.ArmaRepository;
import com.service.apirestful.repositories.PersonajeRepository;
import com.service.apirestful.repositories.ItemRepository;
import com.service.apirestful.repositories.EquipamientoRepository;


@Service
public class EquipamientoService {

    @Autowired
    EquipamientoRepository repositoryEquipamiento;

    @Autowired
    PersonajeRepository repositoryPersonaje;

    @Autowired
    ArmaRepository repositoryArma;

    @Autowired
    ItemRepository repositoryItem;

    public List<Equipamiento> getAllequipamiento() {
        List<Equipamiento> equipamientoList = repositoryEquipamiento.findAll();

        if (equipamientoList.size() > 0) {
            return equipamientoList;
        } else {
            return new ArrayList<Equipamiento>();
        }
    }

    public Equipamiento getEquipamientoById(Long id) throws RecordNotFoundException {
        Optional<Equipamiento> equipamientos = repositoryEquipamiento.findById(id);

        if (equipamientos.isPresent()) {
            return equipamientos.get();
        } else {
            throw new RecordNotFoundException("No equip record exist for given id", id);
        }
    }

    public Equipamiento createEquipamiento(Equipamiento entity) {

        Arma bill = entity.getArma();
        if (bill != null) {
            Arma b = repositoryArma.findById(bill.getId()).get();
            if (b != null) {
                entity.setArma(b);
            }
        }

        Personaje client = entity.getPersonaje();
        if (client != null) {
            Personaje c = repositoryPersonaje.findById(client.getId()).get();
            if (c != null) {
                entity.setpersonaje(c);
            }
        }

        Set<Item> item = new HashSet<>();
        if (entity.getPersonaje()!= null) {
            for (Item ite : entity.getItem()) {
                Item p = repositoryItem.findById(ite.getId()).get();
                if (p == null) {
                    item.add(ite);
                } else {
                    item.add(p);
                }
            }
        }

        entity.setProducts(item);
        entity = repositoryEquipamiento.save(entity);
        return entity;
    }

    public Equipamiento updateEquipamiento(Equipamiento entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Equipamiento> equipamiento = repositoryEquipamiento.findById(entity.getId());

            if (equipamiento.isPresent()) {
                Equipamiento newEntity = equipamiento.get();
                //newEntity.setId(entity.getId());
                Personaje per = entity.getPersonaje();
                if (per != null) {
                    Personaje c = repositoryPersonaje.findById(per.getId()).get();
                    if (c != null) {
                        per = c;
                    }
                    newEntity.setpersonaje(per);
                }

                newEntity.setStatus(entity.getStatus());
             

                Arma arma = entity.getArma();
                if (arma != null) {
                    Arma b = repositoryArma.findById(arma.getId()).get();
                    if (b != null) {
                        arma = b;
                    }
                    newEntity.setArma(arma);
                }

                Set<Item> i = new HashSet<>();
                if (entity.getItem()!= null) {
                    for (Item item : entity.getItem()) {
                        Item p = repositoryItem.findById(item.getId()).get();
                        if (p == null) {
                            i.add(item);
                        } else {
                            i.add(p);
                        }
                    }
                }
                newEntity.setProducts(i);

                newEntity = repositoryEquipamiento.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Orders not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of orders given", 0l);
        }
    }

    public void deleteEquipamientoById(Long id) throws RecordNotFoundException {
        Optional<Equipamiento> eq = repositoryEquipamiento.findById(id);

        if (eq.isPresent()) {
            repositoryEquipamiento.deleteById(id);
        } else {
            throw new RecordNotFoundException("No orders record exist for given id", id);
        }
    }

    public List<Equipamiento> getEquipamientoByCriteria(String status) {
        List<Equipamiento> equipamientoList = repositoryEquipamiento.getByCriteria(status);

        if (equipamientoList.size() > 0) {
            return equipamientoList;
        } else {
            return new ArrayList<Equipamiento>();
        }
    }

}
