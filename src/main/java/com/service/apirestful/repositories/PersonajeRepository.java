/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.repositories;

import com.service.apirestful.model.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonajeRepository extends JpaRepository<Personaje, Long>{
    
    @Query(
    value="SELECT * FROM personaje AS p where p.name like %?1% and p.age like %?2% and p.nv like %?3%",nativeQuery=true)
    public List<Personaje> getByCriteria(String name, String age, String nv);
    
}
