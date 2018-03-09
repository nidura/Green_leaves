/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.desktop.reports.weigh;

import com.sv.desktop.reports.weigh.model.MLoginDetails;
import com.sv.desktop.reports.weigh.model.MRoute;
import com.sv.desktop.reports.weigh.model.MSuplier;
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
public class DWeighService {

    @Autowired
    private DWeighRepository weighRepository;

    @Autowired
    private DSupplierRepository supplierRepository;

    @Autowired
    private DRouteRepository routeRepository;
    
    @Autowired
    private DCollectorRepository collectorRepository;

    public List<MSuplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }

    public List<MRoute> findAllRoutes() {
        return routeRepository.findAll();
    }
    
    public List<MLoginDetails> findAllCollectors() {
        return collectorRepository.findAll();
    }

    public List<Object[]> findByFromdateToDate(String fromDate, String toDate) {
        return weighRepository.findByFromdateToDate(fromDate, toDate);
    }

    public List<Object[]> findByFromDateToDateAndBranch(String fromDate, String toDate, String branch) {
        return weighRepository.findByFromDateToDateAndBranch(fromDate, toDate, branch);
    }

    public List<Object[]> findByFromDateToDateAndBranchAndRoute(String fromDate, String toDate, String branch, String route) {
        return weighRepository.findByFromDateToDateAndBranchAndRoute(fromDate, toDate, branch, route);
    }

    public List<Object[]> findByFromDateTodateAndBranchAndSupplier(String fromDate, String toDate, String branch,String supplier) {
        return weighRepository.findByFromDateTodateAndBranchAndSupplier(fromDate, toDate, branch,supplier);
    }
    
    public List<Object[]> findByFromDateToDateAndBranchAndCollector(String fromDate, String toDate, String branch,String collector) {
        return weighRepository.findByFromDateToDateAndBranchAndCollector(fromDate, toDate, branch,collector);
    }
}
