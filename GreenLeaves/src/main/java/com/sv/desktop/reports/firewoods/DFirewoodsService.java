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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nidura Prageeth
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DFirewoodsService {

    @Autowired
    private DFirewoodsRepository firewoodsRepository;

    @Autowired
    private DBranchRepository branchRepository;

    @Autowired
    private DUnloadPointRepository unloadPointRepository;
    
    @Autowired
    private DFirewoodTypeRepository firewoodTypeRepository;

    public List<Object[]> findReportByBranch(String fromDate, String toDate, String branch, String supno, String rno) {
        return firewoodsRepository.findReportByBranch(fromDate, toDate, branch, supno, rno);
    }

    public List<Object[]> findByFromDateAndToDate(String fromDate, String toDate) {
        return firewoodsRepository.findByFromDateAndToDate(fromDate, toDate);
    }

    public List<Object[]> findByFromDateAndToDateAndBranch(String fromDate, String toDate, String branch) {
        return firewoodsRepository.findByFromDateAndToDateAndBranch(fromDate, toDate, branch);
    }

    public List<Object[]> findByUnloadPointWise(String fromDate, String toDate, String branch) {
        return firewoodsRepository.findByUnloadPointWise(fromDate, toDate, branch);
    }
    
    public List<Object[]> findByUnloadPointBranchWise(String fromDate, String toDate, String branch,String unloadpoint) {
        return firewoodsRepository.findByUnloadPointBranchWise(fromDate, toDate, branch,unloadpoint);
    }
    
    public List<Object[]> findByFirewoodType(String fromDate, String toDate, String branch,String firewoodtype) {
        return firewoodsRepository.findByFirewoodType(fromDate, toDate, branch,firewoodtype);
    }
    

    public List<TFireWood> findAll() {
        return firewoodsRepository.findAll();
    }

    public List<MBranch> findAllBranch() {
        return branchRepository.findAll();
    }

    public List<MFirewoodUnloardPoint> findAllUnloadPoint() {
        return unloadPointRepository.findAll();
    }
    
    public List<MFirewoodType> findAllFirewoodType() {
        return firewoodTypeRepository.findAll();
    }
    
    
    
}
