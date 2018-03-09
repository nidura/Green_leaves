/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.desktop.reports.weigh;

import com.sv.desktop.reports.weigh.model.MRoute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nidura Prageeth
 */
public interface DRouteRepository extends JpaRepository<MRoute, Integer>{
    
}
