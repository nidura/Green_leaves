/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.desktop.reports.firewoods;

import com.sv.desktop.reports.firewoods.model.MBranch;
import com.sv.desktop.reports.firewoods.model.MFirewoodType;
import com.sv.desktop.reports.firewoods.model.MFirewoodUnloardPoint;
import com.sv.desktop.reports.firewoods.model.TFireWood;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nidura Prageeth
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/green-leaves/firewood")
public class DFirewoodsController {

    @Autowired
    private DFirewoodsService firewoodsService;

//    @PersistenceContext
//    EntityManager entityManager;
    @RequestMapping(method = RequestMethod.GET)
    public List<TFireWood> findAll() {
        return firewoodsService.findAll();
    }

    @RequestMapping(path = "/find-report/{date1}/{date2}/{branch}/{supno}/{rno}", method = RequestMethod.GET)
    public List<Object[]> findReportByBranch(@PathVariable(name = "date1") String date1, @PathVariable(name = "date2") String date2, @PathVariable(name = "branch") String branch, @PathVariable(name = "supno") String supno, @PathVariable(name = "rno") String rno) {
        return firewoodsService.findReportByBranch(date1, date2, branch, supno, rno);
    }
    
    @RequestMapping(path = "/from-to-date/{date1}/{date2}", method = RequestMethod.GET)
    public List<Object[]> findByFromDateAndToDate(@PathVariable(name = "date1") String date1, @PathVariable(name = "date2") String date2) {
        return firewoodsService.findByFromDateAndToDate(date1, date2);
    }
   
    @RequestMapping(path = "/find-by-date-branch/{date1}/{date2}/{branch}", method = RequestMethod.GET)
    public List<Object[]> findByFromDateAndToDate(@PathVariable(name = "date1") String date1, @PathVariable(name = "date2") String date2,@PathVariable(name = "branch") String branch) {
        return firewoodsService.findByFromDateAndToDateAndBranch(date1, date2,branch);
    }
    
    @RequestMapping(path = "/find-unloadpoint-wise/{date1}/{date2}/{branch}", method = RequestMethod.GET)
    public List<Object[]> findByUnloadPointWise(@PathVariable(name = "date1") String date1, @PathVariable(name = "date2") String date2,@PathVariable(name = "branch") String branch) {
        return firewoodsService.findByUnloadPointWise(date1, date2,branch);
    }
    
    @RequestMapping(path = "/find-unloadpoint-branchwise/{date1}/{date2}/{branch}/{unloadpoint}", method = RequestMethod.GET)
    public List<Object[]> findByUnloadPointBranchWise(@PathVariable(name = "date1") String date1, @PathVariable(name = "date2") String date2,@PathVariable(name = "branch") String branch,@PathVariable(name = "unloadpoint") String unloadpoint) {
        return firewoodsService.findByUnloadPointBranchWise(date1, date2,branch,unloadpoint);
    }
    
    @RequestMapping(path = "/find-firewood-type/{date1}/{date2}/{branch}/{firewoodtype}", method = RequestMethod.GET)
    public List<Object[]> findByFirewoodType(@PathVariable(name = "date1") String date1, @PathVariable(name = "date2") String date2,@PathVariable(name = "branch") String branch,@PathVariable(name = "firewoodtype") String firewoodtype) {
        return firewoodsService.findByFirewoodType(date1, date2,branch,firewoodtype);
    }

    //other 
//    @RequestMapping(path = "/all-branch", method = RequestMethod.GET)
//    public List<MBranch> findAllBranch() {
//        TypedQuery<MBranch> query = entityManager.createNamedQuery("MBranch.findAll", MBranch.class);
//        List<MBranch> results = query.getResultList();
//        return results;
//    }
    @RequestMapping(path = "/all-branch", method = RequestMethod.GET)
    public List<MBranch> findAllBranch() {
        return firewoodsService.findAllBranch();
    }
    
    @RequestMapping(path = "/all-unloadpoint", method = RequestMethod.GET)
    public List<MFirewoodUnloardPoint> findAllUnloadPoint() {
        return firewoodsService.findAllUnloadPoint();
    }
    
    @RequestMapping(path = "/all-firewood-type", method = RequestMethod.GET)
    public List<MFirewoodType> findAllFirewoodType() {
        return firewoodsService.findAllFirewoodType();
    }
}
