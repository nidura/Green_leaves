/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.desktop.reports.weigh;

import com.sv.desktop.reports.weigh.model.MSuplier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nidura Prageeth
 */
public interface DSupplierRepository extends JpaRepository<MSuplier, Integer>{
    
}
