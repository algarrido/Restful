/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.controllers;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Arma;
import com.service.apirestful.services.ArmaService;
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
@RequestMapping("/arma")
public class ArmaServiceController {

    @Autowired
    ArmaService service;

    @GetMapping
    public ResponseEntity<List<Arma>> getAllArma() {
        List<Arma> list = service.getAllArma();

        return new ResponseEntity<List<Arma>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arma> getArmaById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Arma entity = service.getArmaById(id);

        return new ResponseEntity<Arma>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search/{code}")
    public ResponseEntity<List<Arma>> getArmaByCode(@PathVariable("code") String code) {
        List<Arma> list = service.getArmaByCode(code);

        return new ResponseEntity<List<Arma>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Arma> createArma(@Valid @RequestBody Arma myArma) {
        Arma created = service.createArma(myArma);
        return new ResponseEntity<Arma>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Arma> UpdateArma(@Valid @RequestBody Arma myArma)
            throws RecordNotFoundException {
        Arma updated = service.updateArma(myArma);
        return new ResponseEntity<Arma>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteArmaById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteArmaById(id);
        return HttpStatus.ACCEPTED;
    }
    @GetMapping("/search")
    public ResponseEntity<List<Arma>> getProductByCriteria(@RequestParam(required = false, name = "max") String max, @RequestParam(required = false, name = "min") String min) {
        List<Arma> list = null;
        if (max == null && min == null) {
            list = new ArrayList<>();
        } else {
            if (max == null) {
             //   list = service.getByMoreTotalPrice(min);
            } else {
                if (min == null) {
               //     list = service.getByLessTotalPrices(max);
                } else {
                 //   list = service.getBetweenTotalPrices(max, min);
                }
            }
        }
        return new ResponseEntity<List<Arma>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
