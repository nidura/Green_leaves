/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.desktop.reports.firewoods;

import com.sv.desktop.reports.firewoods.model.TFireWood;
import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nidura Prageeth
 */
public interface DFirewoodsRepository extends JpaRepository<TFireWood, Integer> {

    @Query(value = "select \n"
            + "m_route.rname,\n"
            + "m_branch.name,\n"
            + "m_suplier.supname,\n"
            + "(select sum(t_fire_wood.qty) from t_fire_wood \n"
            + "left join m_suplier sup on sup.index_no=t_fire_wood.supplier\n"
            + "where sup.index_no=t.supplier and date(t_fire_wood.date) between :fromDate and :toDate) as qty,\n"
            + "(select sum(t_fire_wood.amount) from t_fire_wood \n"
            + "left join m_suplier sup on sup.index_no=t_fire_wood.supplier\n"
            + "where sup.index_no=t.supplier and date(t_fire_wood.date) between :fromDate and :toDate) as amount,\n"
            + "m_firewood_type.rate\n"
            + "from\n"
            + "t_fire_wood t\n"
            + "left join\n"
            + "m_suplier on m_suplier.supno=t.supplier\n"
            + "left join\n"
            + "m_branch on m_branch.index_no=t.branch\n"
            + "left join\n"
            + "m_firewood_type on m_firewood_type.index_no=t.firewood_type\n"
            + "left join\n"
            + "m_route on m_route.index_no=m_suplier.route\n"
            + "where \n"
            + "date(t.date) between :fromDate and :toDate\n"
            + "and (:branch = 'null' or m_branch.index_no = :branch)\n"
            + "and (:supno = 'null' or m_suplier.supno=:supno)\n"
            + "and (:rno = 'null' or m_route.index_no=:rno)\n"
            + "group by t.supplier order by m_branch.index_no", nativeQuery = true)
    public List<Object[]> findReportByBranch(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
            @Param("branch") String branch, @Param("supno") String supno, @Param("rno") String rno);

    @Query(value = "select \n"
            + "m.index_no,\n"
            + "m.name,\n"
            + "(select ifnull(sum(t_fire_wood.qty) ,0.00) from t_fire_wood where t_fire_wood.branch=m.index_no and\n"
            + "date(t_fire_wood.date) between :fromDate and :toDate) as qty,\n"
            + "(select ifnull(sum(t_fire_wood.amount),0.00) from t_fire_wood where t_fire_wood.branch=m.index_no and\n"
            + "date(t_fire_wood.date) between :fromDate and :toDate) as amount\n"
            + "from\n"
            + "m_branch m\n"
            + "left join\n"
            + "t_fire_wood on t_fire_wood.branch=m.index_no\n"
            + "group by m.index_no\n"
            + "order by m.index_no", nativeQuery = true)
    public List<Object[]> findByFromDateAndToDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value = "select\n"
            + "m_branch.name,\n"
            + "t.date,\n"
            + "sum(t.qty) as qty,\n"
            + "sum(t.amount) as amount\n"
            + "from\n"
            + "t_fire_wood t\n"
            + "left join\n"
            + "m_branch on m_branch.index_no=t.branch\n"
            + "where \n"
            + "date(t.date) between :fromDate and :toDate\n"
            + "and (:branch  = 'null' or m_branch.index_no =:branch)\n"
            + "group by date(t.date)", nativeQuery = true)
    public List<Object[]> findByFromDateAndToDateAndBranch(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("branch") String branch);

    @Query(value = "select \n"
            + "t.name,\n"
            + "m_branch.name as branch,\n"
            + "(select  ifnull(sum(t_fire_wood.qty),0.00)  from t_fire_wood \n"
            + "left join m_firewood_unloard_point  on m_firewood_unloard_point.index_no=t_fire_wood.firewood_unloard_point\n"
            + "where t.index_no=t_fire_wood.firewood_unloard_point and date(t_fire_wood.date) between :fromDate and :toDate) as qty\n"
            + "from\n"
            + "m_firewood_unloard_point t\n"
            + "left join\n"
            + "t_fire_wood on  t_fire_wood.firewood_unloard_point = t.index_no\n"
            + "left join\n"
            + "m_branch on m_branch.index_no=t.branch\n"
            + "where \n"
            + "(:branch  = 'null' or m_branch.index_no =:branch)\n"
            + "group by t.index_no\n"
            + "order by t.index_no", nativeQuery = true)
    public List<Object[]> findByUnloadPointWise(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("branch") String branch);

    @Query(value = "select\n"
            + "m_branch.name as branch,\n"
            + "f.name,\n"
            + "(select IFNULL(sum(t_fire_wood.qty),0.00) from t_fire_wood \n"
            + "left join \n"
            + "m_firewood_type on m_firewood_type.index_no=t_fire_wood.firewood_type \n"
            + "left join\n"
            + "m_firewood_unloard_point on m_firewood_unloard_point.index_no=t_fire_wood.firewood_unloard_point\n"
            + "where m_firewood_type.index_no=f.index_no \n"
            + "and date(t_fire_wood.date) between :fromDate and :toDate\n"
            + "and m_firewood_unloard_point.index_no= :unloadpoint) as qty\n"
            + "from \n"
            + "m_firewood_type f\n"
            + "left join\n"
            + "t_fire_wood on t_fire_wood.firewood_type=f.index_no\n"
            + "left join\n"
            + "m_branch on m_branch.index_no=f.branch\n"
            + "where m_branch.index_no = :branch\n"
            + "group by f.index_no", nativeQuery = true)
    public List<Object[]> findByUnloadPointBranchWise(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("branch") String branch, @Param("unloadpoint") String unloadpoint);

    @Query(value = "select\n"
            + "m_branch.name,\n"
            + "t.date,\n"
            + "sum(t.qty) as qty\n"
            + "from\n"
            + "t_fire_wood t\n"
            + "left join\n"
            + "m_branch on m_branch.index_no=t.branch\n"
            + "left join\n"
            + "m_firewood_type on m_firewood_type.index_no=t.firewood_type\n"
            + "where \n"
            + "date(t.date) between :fromDate and :toDate\n"
            + "and (:branch  = 'null' or m_branch.index_no =:branch)\n"
            + "and (:firewoodtype  = 'null' or m_firewood_type.index_no =:firewoodtype)\n"
            + "group by date(t.date)", nativeQuery = true)
    public List<Object[]> findByFirewoodType(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("branch") String branch, @Param("firewoodtype") String firewoodtype);

}
