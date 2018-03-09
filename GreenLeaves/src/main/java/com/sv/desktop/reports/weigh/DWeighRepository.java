/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.desktop.reports.weigh;

import com.sv.desktop.reports.weigh.model.TGreenLeavesWeigh;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nidura Prageeth
 */
public interface DWeighRepository extends JpaRepository<TGreenLeavesWeigh, Integer> {

    @Query(value = "select \n"
            + "m.name as branch,\n"
            + "(select ifnull(sum(t_green_leaves_weigh.total_weight),0.00) from t_green_leaves_weigh\n"
            + "left join m_branch on m_branch.index_no=t_green_leaves_weigh.branch \n"
            + "where\n"
            + "date(t_green_leaves_weigh.date) between :fromDate and :toDate \n"
            + "and m_branch.index_no=m.index_no) as gross_qty,\n"
            + "(select ifnull(sum(t_green_leaves_weigh.general_deduction +t_green_leaves_weigh.tare_deduction\n"
            + "+t_green_leaves_weigh.water_deduction+ t_green_leaves_weigh.boiled_leaves + t_green_leaves_weigh.coarse_leaves),0.00) from t_green_leaves_weigh\n"
            + "left join m_branch on m_branch.index_no=t_green_leaves_weigh.branch \n"
            + "where\n"
            + "date(t_green_leaves_weigh.date) between :fromDate and :toDate \n"
            + "and m_branch.index_no=m.index_no) as deduction,\n"
            + "(select ifnull(sum(t_green_leaves_weigh.net_weight),0.00) from t_green_leaves_weigh\n"
            + "left join m_branch on m_branch.index_no=t_green_leaves_weigh.branch \n"
            + "where\n"
            + "date(t_green_leaves_weigh.date) between :fromDate and :toDate \n"
            + "and m_branch.index_no=m.index_no)as net_qty\n"
            + "from \n"
            + "m_branch m\n"
            + "left join\n"
            + "t_green_leaves_weigh on t_green_leaves_weigh.branch = m.index_no\n"
            + "group by m.index_no\n"
            + "order by m.index_no", nativeQuery = true)
    public List<Object[]> findByFromdateToDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value = "select \n"
            + "m.rname as route,\n"
            + "(select ifnull(sum(t_green_leaves_weigh.total_weight),0.00) from t_green_leaves_weigh\n"
            + "left join m_route on m_route.index_no=t_green_leaves_weigh.route\n"
            + "where\n"
            + "date(t_green_leaves_weigh.date) between :fromDate and :toDate \n"
            + "and m_route.index_no=m.index_no) as gross_qty,\n"
            + "(select ifnull(sum(t_green_leaves_weigh.general_deduction +t_green_leaves_weigh.tare_deduction\n"
            + "+t_green_leaves_weigh.water_deduction+ t_green_leaves_weigh.boiled_leaves + t_green_leaves_weigh.coarse_leaves),0.00) from t_green_leaves_weigh\n"
            + "left join m_route on m_route.index_no=t_green_leaves_weigh.route\n"
            + "where\n"
            + "date(t_green_leaves_weigh.date) between :fromDate and :toDate \n"
            + "and m_route.index_no=m.index_no)  as deduction,\n"
            + "(select ifnull(sum(t_green_leaves_weigh.net_weight),0.00) from t_green_leaves_weigh\n"
            + "left join m_route on m_route.index_no=t_green_leaves_weigh.route\n"
            + "where\n"
            + "date(t_green_leaves_weigh.date) between :fromDate and :toDate \n"
            + "and m_route.index_no=m.index_no) as net_qty\n"
            + "from \n"
            + "m_route m\n"
            + "left join\n"
            + "m_branch on m_branch.index_no = m.branch\n"
            + "left join\n"
            + "t_green_leaves_weigh on t_green_leaves_weigh.route = m.index_no\n"
            + "where\n"
            + "m_branch.index_no = :branch\n"
            + "group by m.index_no\n"
            + "order by m.index_no", nativeQuery = true)
    public List<Object[]> findByFromDateToDateAndBranch(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("branch") String branch);

    @Query(value = "select \n"
            + "m.Date as date,\n"
            + "sum(m.total_weight) as gross_qty,\n"
            + "sum(m.general_deduction +m.tare_deduction\n"
            + "+m.water_deduction+ m.boiled_leaves + m.coarse_leaves) as dedcution,\n"
            + "sum(m.net_weight) as net_qty\n"
            + "from \n"
            + "t_green_leaves_weigh m\n"
            + "left join\n"
            + "m_branch on m_branch.index_no = m.branch\n"
            + "left join\n"
            + "m_suplier on m_suplier.index_no = m.supplier\n"
            + "where\n"
            + "m_branch.index_no = :branch\n"
            + "and \n"
            + "m_suplier.index_no = :supplier\n"
            + "and\n"
            + "date(m.date) between :fromDate and :toDate \n"
            + "group by date(m.date)\n"
            + "order by m.date", nativeQuery = true)
    public List<Object[]> findByFromDateTodateAndBranchAndSupplier(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("branch") String branch, @Param("supplier") String supplier);

    @Query(value = "select \n"
            + "m.Date as date,\n"
            + "sum(m.total_weight) as gross_qty,\n"
            + "sum(m.general_deduction +m.tare_deduction\n"
            + "+m.water_deduction+ m.boiled_leaves + m.coarse_leaves) as dedcution,\n"
            + "sum(m.net_weight) as net_qty\n"
            + "from \n"
            + "t_green_leaves_weigh m\n"
            + "left join\n"
            + "m_branch on m_branch.index_no = m.branch\n"
            + "left join\n"
            + "m_route on m_route.index_no = m.route\n"
            + "where\n"
            + "m_branch.index_no = :branch\n"
            + "and \n"
            + "m_route.index_no = :route\n"
            + "and\n"
            + "date(m.date) between :fromDate and :toDate \n"
            + "group by date(m.date)\n"
            + "order by m.date", nativeQuery = true)
    public List<Object[]> findByFromDateToDateAndBranchAndRoute(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("branch") String branch, @Param("route") String route);

    @Query(value = "select \n"
            + "m.Date as date,\n"
            + "sum(m.total_weight) as gross_qty,\n"
            + "sum(m.general_deduction +m.tare_deduction\n"
            + "+m.water_deduction+ m.boiled_leaves + m.coarse_leaves) as dedcution,\n"
            + "sum(m.net_weight) as net_qty\n"
            + "from \n"
            + "t_green_leaves_weigh m\n"
            + "left join\n"
            + "m_branch on m_branch.index_no = m.branch\n"
            + "left join\n"
            + "m_login_details on m_login_details.index_no = m.login_user\n"
            + "where\n"
            + "m_branch.index_no = :branch\n"
            + "and \n"
            + "m_login_details.index_no = :collector\n"
            + "and\n"
            + "date(m.date) between :fromDate and :toDate \n"
            + "group by date(m.date)\n"
            + "order by m.date ", nativeQuery = true)
    public List<Object[]> findByFromDateToDateAndBranchAndCollector(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("branch") String branch, @Param("collector") String collector);
}
