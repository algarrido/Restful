/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Equipamiento;
import com.service.apirestful.model.Item;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.apirestful.repositories.ItemRepository;
import com.service.apirestful.repositories.EquipamientoRepository;


@Service
public class ItemService {

    @Autowired
    ItemRepository repositoryItem;
    @Autowired
    EquipamientoRepository repositoryEquipamiento;

    public List<Item> getAllItem() {
        List<Item> itemList = repositoryItem.findAll();

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<Item>();
        }
    }

    public Item getItemById(Long id) throws RecordNotFoundException {
        Optional<Item> item = repositoryItem.findById(id);

        if (item.isPresent()) {
            return item.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id", id);
        }
    }

    public Item createItem(Item entity) {
        Set<Equipamiento> equipamiento = new HashSet<>();
        Equipamiento o;
        if (entity.getEquipamientos() != null) {
            for (Equipamiento order : entity.getEquipamientos()) {
                o = repositoryEquipamiento.findById(order.getId()).get();
                if (o == null) {
                    equipamiento.add(order);
                } else {
                    equipamiento.add(o);
                }
            }
        }

        entity.setEquipamiento(equipamiento);
        entity = repositoryItem.save(entity);
        return entity;
    }

    public Item updateItem(Item entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Item> item = repositoryItem.findById(entity.getId());

            if (item.isPresent()) {
                Item newEntity = item.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setDescription(entity.getDescription());
                newEntity.setPrice(entity.getPrice());

                Set<Equipamiento> equipamientos = new HashSet<>();
                if (entity.getEquipamientos()!= null) {
                    for (Equipamiento equipamiento : entity.getEquipamientos()) {
                        Equipamiento o = repositoryEquipamiento.findById(equipamiento.getId()).get();
                        if (o == null) {
                            equipamientos.add(equipamiento);
                        } else {
                            equipamientos.add(o);
                        }
                    }
                }
                newEntity.setEquipamiento(equipamientos);

                newEntity = repositoryItem.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Item not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of item given", 0l);
        }
    }

    public void deleteItemById(Long id) throws RecordNotFoundException {
        Optional<Item> Item = repositoryItem.findById(id);

        if (Item.isPresent()) {
            repositoryItem.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Item record exist for given id", id);
        }
    }

}
