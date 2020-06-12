/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.repositories;

import com.service.apirestful.model.Equipamiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EquipamientoRepository extends JpaRepository<Equipamiento, Long>{
    
    @Query(
    value="SELECT * FROM equipamiento AS e where e.status like %?2%",nativeQuery=true)
    public List<Equipamiento> getByCriteria( String status);
    
}
