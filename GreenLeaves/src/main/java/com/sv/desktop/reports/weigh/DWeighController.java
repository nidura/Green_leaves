/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.desktop.reports.weigh;

import com.sv.desktop.reports.weigh.model.MLoginDetails;
import com.sv.desktop.reports.weigh.model.MRoute;
import com.sv.desktop.reports.weigh.model.MSuplier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping(path = "/api/green-leaves/weigh")
public class DWeighController {

    @Autowired
    private DWeighService weighService;

    @RequestMapping(path = "/all-suppliers", method = RequestMethod.GET)
    public List<MSuplier> findAllSuppliers() {
        return weighService.findAllSuppliers();
    }

    @RequestMapping(path = "/all-routes", method = RequestMethod.GET)
    public List<MRoute> findAllRoutes() {
        return weighService.findAllRoutes();
    }

    @RequestMapping(path = "/all-collectors", method = RequestMethod.GET)
    public List<MLoginDetails> findAllCollectors() {
        return weighService.findAllCollectors();
    }

    @RequestMapping(path = "/findby-fromdate-todate/{fromdate}/{todate}", method = RequestMethod.GET)
    public List<Object[]> findByFromdateToDate(@PathVariable("fromdate") String fromDate, @PathVariable("todate") String toDate) {
        return weighService.findByFromdateToDate(fromDate, toDate);
    }

    @RequestMapping(path = "/findby-branch/{fromdate}/{todate}/{branch}", method = RequestMethod.GET)
    public List<Object[]> findByFromDateToDateAndBranch(@PathVariable("fromdate") String fromDate, @PathVariable("todate") String toDate, @PathVariable("branch") String branch) {
        return weighService.findByFromDateToDateAndBranch(fromDate, toDate, branch);
    }

    @RequestMapping(path = "/findby-route/{fromdate}/{todate}/{branch}/{route}", method = RequestMethod.GET)
    public List<Object[]> findByFromDateToDateAndBranchAndRoute(@PathVariable("fromdate") String fromDate, @PathVariable("todate") String toDate, @PathVariable("branch") String branch, @PathVariable("route") String route) throws ParseException {
        return weighService.findByFromDateToDateAndBranchAndRoute(fromDate, toDate, branch, route);
    }

    @RequestMapping(path = "/findby-supplier/{fromdate}/{todate}/{branch}/{supplier}", method = RequestMethod.GET)
    public List<Object[]> findByFromDateTodateAndBranchAndSupplier(@PathVariable("fromdate") String fromDate, @PathVariable("todate") String toDate, @PathVariable("branch") String branch, @PathVariable("supplier") String supplier) {
        return weighService.findByFromDateTodateAndBranchAndSupplier(fromDate, toDate, branch, supplier);
    }

    @RequestMapping(path = "/findby-collector/{fromdate}/{todate}/{branch}/{collector}", method = RequestMethod.GET)
    public List<Object[]> findByFromDateToDateAndBranchAndCollector(@PathVariable("fromdate") String fromDate, @PathVariable("todate") String toDate, @PathVariable("branch") String branch, @PathVariable("collector") String collector) {
        return weighService.findByFromDateToDateAndBranchAndCollector(fromDate, toDate, branch, collector);
    }

    //others
    @RequestMapping(path = "/all-dates/{fromdate}/{todate}", method = RequestMethod.GET)
    public List<Date> findAllDates(@PathVariable("fromdate") String fromDate, @PathVariable("todate") String toDate) throws ParseException {
//        String s = fromDate;
//        String e = toDate;
//        LocalDate start = LocalDate.parse(s);
//        LocalDate end = LocalDate.parse(e);
//        List<LocalDate> totalDates = new ArrayList<>();
//
//        while (!start.isAfter(end)) {
//            totalDates.add(start);
//            start = start.plusDays(1);
//        }
//        return totalDates;
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
        calendar.setTime(date1);

        while (calendar.getTime().before(date2)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        dates.add(date2);
        return dates;
    }

}
