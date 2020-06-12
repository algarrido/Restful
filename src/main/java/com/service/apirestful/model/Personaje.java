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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "personaje")
public class Personaje implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;
   
    @Column(name = "age")
    private int age;
    
    @Column(name = "nv")
    private String nv;
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "personaje", fetch = FetchType.LAZY)
    private Set<Equipamiento> equipamiento;
    
    public Personaje() {
    }

    public Personaje(Long id) {
        this.id = id;
    }

    public Personaje(Long id, String name, int age, String nivel) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nv = nivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNV() {
        return nv;
    }

    public void setNV(String nv) {
        this.nv = nv;
    }
    
    public Set<Equipamiento> getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(Set<Equipamiento> equi) {
        this.equipamiento = equi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + this.age;
        hash = 13 * hash + Objects.hashCode(this.nv);
        hash = 13 * hash + Objects.hashCode(this.equipamiento);
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
        final Personaje other = (Personaje) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.nv, other.nv)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.equipamiento, other.equipamiento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Personaje{" + "id=" + id + ", name=" + name + ", age=" + age + ", nivel=" + nv + ", equipamiento=" + equipamiento + '}';
    }
  
}
