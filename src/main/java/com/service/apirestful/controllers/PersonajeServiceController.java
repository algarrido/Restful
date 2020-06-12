/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.controllers;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Personaje;
import com.service.apirestful.services.PersonajeService;
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
@RequestMapping("/personaje")
public class PersonajeServiceController {

    @Autowired
    PersonajeService service;

    @GetMapping
    public ResponseEntity<List<Personaje>> getAllClient() {
        List<Personaje> list = service.getAllPersonaje();

        return new ResponseEntity<List<Personaje>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaje> getPersonajeById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Personaje entity = service.getPersonajeById(id);

        return new ResponseEntity<Personaje>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Personaje>> getPersonajeByCriteria(@RequestParam(required = false,name="name") String name,@RequestParam(required = false,name="age") String age,@RequestParam(required = false,name="phone") String phone) {
       
        List<Personaje> list = service.getPersonajeByCriteria(name==null?"":name,age==null?"":age,phone==null?"":phone);

        return new ResponseEntity<List<Personaje>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Personaje> createPersonaje(@Valid @RequestBody Personaje myClient) {
        Personaje created = service.createPersonaje(myClient);
        return new ResponseEntity<Personaje>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Personaje> UpdatePersonaje(@Valid @RequestBody Personaje myClient)
            throws RecordNotFoundException {
        Personaje updated = service.updatePersonaje(myClient);
        return new ResponseEntity<Personaje>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePersonajeById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deletePersonajeById(id);
        return HttpStatus.ACCEPTED;
    }
}
