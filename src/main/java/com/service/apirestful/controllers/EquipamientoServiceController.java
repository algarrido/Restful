/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.controllers;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Equipamiento;
import com.service.apirestful.services.EquipamientoService;
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
@RequestMapping("/equipamiento")
public class EquipamientoServiceController {

    @Autowired
    EquipamientoService service;

    @GetMapping
    public ResponseEntity<List<Equipamiento>> getAllEquipamiento() {
        List<Equipamiento> list = service.getAllequipamiento();

        return new ResponseEntity<List<Equipamiento>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamiento> getEquipamientoById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Equipamiento entity = service.getEquipamientoById(id);

        return new ResponseEntity<Equipamiento>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Equipamiento>> getEquipamientoByCriteria(@RequestParam(required = false,name="status") String status) {
        List<Equipamiento> list = service.getEquipamientoByCriteria(status==null?"":status);
        return new ResponseEntity<List<Equipamiento>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Equipamiento> createEquipamiento(@Valid @RequestBody Equipamiento myOrders) {
        Equipamiento created = service.createEquipamiento(myOrders);
        return new ResponseEntity<Equipamiento>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Equipamiento> UpdateEquipamiento(@Valid @RequestBody Equipamiento myEquipamiento)
            throws RecordNotFoundException {
        Equipamiento updated = service.updateEquipamiento(myEquipamiento);
        return new ResponseEntity<Equipamiento>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteEquipamientoById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteEquipamientoById(id);
        return HttpStatus.ACCEPTED;
    }

}