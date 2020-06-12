    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "arma")
public class Arma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "rareza")
    private String rareza;

    @Column(name = "totalPrice")
    private int totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipamiento", referencedColumnName = "id")
    private Equipamiento equipamiento;

    public Arma() {
    }

    public Arma(Long id) {
        this.id = id;
    }

    public Arma(Long id, String rareza, Date datePurchase, int numProduct, int totalPrice) {
        this.id = id;
        this.rareza = rareza;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Equipamiento getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(Equipamiento orders) {
        this.equipamiento = equipamiento;
    }

    public String getrareza() {
        return rareza;
    }

    public void setRareza(String raString) {
        this.rareza = rareza;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.rareza);
        hash = 59 * hash + this.totalPrice;
        hash = 59 * hash + Objects.hashCode(this.equipamiento);
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
        final Arma other = (Arma) obj;
       
        if (this.totalPrice != other.totalPrice) {
            return false;
        }
        if (!Objects.equals(this.rareza, other.rareza)) {
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
        return "Bill{" + "id=" + id + ", rareza=" + rareza +  ", totalPrice=" + totalPrice + ", equipamiento=" + equipamiento + '}';
    }

    

}
