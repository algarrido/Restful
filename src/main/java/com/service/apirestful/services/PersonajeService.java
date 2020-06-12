/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Personaje;
import com.service.apirestful.model.Equipamiento;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.apirestful.repositories.PersonajeRepository;
import com.service.apirestful.repositories.EquipamientoRepository;


@Service
public class PersonajeService {

    @Autowired
    PersonajeRepository repositoryPersonaje;

    @Autowired
    EquipamientoRepository repositoryEquipamiento;

    public List<Personaje> getAllPersonaje() {
        List<Personaje> personajeList = repositoryPersonaje.findAll();

        if (personajeList.size() > 0) {
            return personajeList;
        } else {
            return new ArrayList<Personaje>();
        }
    }

    public Personaje getPersonajeById(Long id) throws RecordNotFoundException {
        Optional<Personaje> personaje = repositoryPersonaje.findById(id);

        if (personaje.isPresent()) {
            return personaje.get();
        } else {
            throw new RecordNotFoundException("No perso record exist for given id", id);
        }
    }

    public Personaje createPersonaje(Personaje entity) {
        Set<Equipamiento> equipamientos = new HashSet<>();
        Equipamiento o;
        if (entity.getEquipamiento() != null) {
            for (Equipamiento equipamiento : entity.getEquipamiento()) {
                o = repositoryEquipamiento.findById(equipamiento.getId()).get();
                if (o == null) {
                    equipamientos.add(equipamiento);
                } else {
                    equipamientos.add(o);
                }
            }
        }

        entity.setEquipamiento(equipamientos);
        entity = repositoryPersonaje.save(entity);
        return entity;
    }

    public Personaje updatePersonaje(Personaje entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Personaje> client = repositoryPersonaje.findById(entity.getId());

            if (client.isPresent()) {
                Personaje newEntity = client.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setAge(entity.getAge());
                newEntity.setNV(entity.getNV());

                Set<Equipamiento> orders = new HashSet<>();
                if (entity.getEquipamiento()!= null) {
                    for (Equipamiento order : entity.getEquipamiento()) {
                        Equipamiento o = repositoryEquipamiento.findById(order.getId()).get();
                        if (o == null) {
                            orders.add(order);
                        } else {
                            orders.add(o);
                        }
                    }
                }
                newEntity.setEquipamiento(orders);

                newEntity = repositoryPersonaje.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Pers not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of client given", 0l);
        }
    }

    public void deletePersonajeById(Long id) throws RecordNotFoundException {
        Optional<Personaje> personaje = repositoryPersonaje.findById(id);

        if (personaje.isPresent()) {
            repositoryPersonaje.deleteById(id);
        } else {
            throw new RecordNotFoundException("No per record exist for given id", id);
        }
    }

    public List<Personaje> getPersonajeByCriteria(String name, String age, String phone) {
        List<Personaje> personajeList = repositoryPersonaje.getByCriteria(name, age, phone);

        if (personajeList.size() > 0) {
            return personajeList;
        } else {
            return new ArrayList<Personaje>();
        }
    }

}
