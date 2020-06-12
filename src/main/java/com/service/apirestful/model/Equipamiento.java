/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "equipamiento")
public class Equipamiento implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
  
    @Column(name = "status")
    private String status;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "equipamiento", fetch = FetchType.LAZY)
    private Arma arma;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personaje", referencedColumnName = "id")
    private Personaje personaje;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "equipamiento_item", 
        joinColumns = @JoinColumn(name = "id_personaje",referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "id_item",referencedColumnName = "id"))
    private Set<Item> items;

    public Equipamiento() {
    }

    public Equipamiento(Long id) {
        this.id = id;
    }

    public Equipamiento(Long id, String status, Arma arma, Personaje personaje, Set<Item> item) {
        this.id = id;
       
        this.status = status;
        this.arma = arma;
        this.personaje = personaje;
        this.items = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setpersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Set<Item> getItem() {
        return items;
    }

    public void setProducts(Set<Item> products) {
        this.items = products;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.status);
        hash = 79 * hash + Objects.hashCode(this.arma);
        hash = 79 * hash + Objects.hashCode(this.personaje);
        hash = 79 * hash + Objects.hashCode(this.items);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipamiento other = (Equipamiento) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.arma, other.arma)) {
            return false;
        }
        if (!Objects.equals(this.personaje, other.personaje)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipamiento{" + "id=" + id + ", status=" + status + ", arma=" + arma + ", personaje=" + personaje + ", item=" + items + '}';
    }
    
    
    
}
