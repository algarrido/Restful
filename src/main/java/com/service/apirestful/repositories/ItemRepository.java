/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.repositories;

import com.service.apirestful.model.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ItemRepository extends JpaRepository<Item, Long>{
    
    @Query(
    value="SELECT * FROM item AS i WHERE i.name LIKE %?1%",
            nativeQuery=true)
    public List<Item> getByName(String name);
 
    
}
