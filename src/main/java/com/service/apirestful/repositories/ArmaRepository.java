/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.repositories;

import com.service.apirestful.model.Arma;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ArmaRepository extends JpaRepository<Arma, Long>{
    
    @Query(
            value = "SELECT * FROM arma AS a WHERE a.code LIKE %?1%",
            nativeQuery = true)
    public List<Arma> getByCode(String code);
    
/*    @Query(
    value="SELECT * FROM arma AS b WHERE b.totalPrice < %?1%",
            nativeQuery=true)
    public List<Arma> getLessTotalPrice(String price);
    
    @Query(
    value="SELECT * FROM arma AS b WHERE b.totalPrice > %?1%",
            nativeQuery=true)
    public List<Arma> getMoreTotalPrice(String price);
    
    @Query(
    value="SELECT * FROM arma AS b WHERE b.totalPrice < %?1% AND b.totalPrice > %?2%",
            nativeQuery=true)
    public List<Arma> getPriceTotalBetween(String maximum, String minimun); */
    
}
