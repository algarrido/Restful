/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.controllers;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Item;
import com.service.apirestful.services.ItemService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemServiceController {

    @Autowired
    ItemService service;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItem() {
        List<Item> list = service.getAllItem();

        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Item entity = service.getItemById(id);

        return new ResponseEntity<Item>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
   
    @GetMapping("/search")
    public ResponseEntity<List<Item>> getItemByCriteria(@RequestParam(required = false, name = "name") String name, @RequestParam(required = false, name = "max") String max, @RequestParam(required = false, name = "min") String min) {
        List<Item> list = null;
        if (name == null && max == null && min == null) {
            list = new ArrayList<>();
        } else {
            if (max == null) {
                if (name == null) {
                  //  list = service.getByMorePrice(min);
                } else {
                    if (min == null) {
                      //  list = service.getByName(name);
                    } else {
                 //       list = service.getByNameMinPrice(name, min);
                    }
                }
            } else {
                if (name == null) {
                    if (min == null) {
                    //    list = service.getByLessPrices(max);
                    } else {
                   //     list = service.getBetweenPrices(max, min);
                    }
                } else {
                    if (min == null) {
                //        list = service.getByNameMaxPrice(name, max);
                    } else {
                  //      list = service.getByNamePrices(name, max, min);
                    }
                }
            }
        }

        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item myItem) {
        Item created = service.createItem(myItem);
        return new ResponseEntity<Item>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Item> UpdateItem(@Valid @RequestBody Item myItem)
            throws RecordNotFoundException {
        Item updated = service.updateItem(myItem);
        return new ResponseEntity<Item>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteItemById(id);
        return HttpStatus.ACCEPTED;
    }
}
