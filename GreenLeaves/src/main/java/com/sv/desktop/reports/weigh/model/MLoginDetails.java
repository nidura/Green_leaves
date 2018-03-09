/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.desktop.reports.weigh.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nidura Prageeth
 */
@Entity
@Table(name = "m_login_details", catalog = "supervis_green_leaves_main", schema = "")
public class MLoginDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lgno")
    private int lgno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "active")
    private String active;
    @Column(name = "default_route")
    private Integer defaultRoute;
    @Column(name = "default_vehicle")
    private Integer defaultVehicle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "firewood_serial")
    private int firewoodSerial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "green_leaves_serial")
    private int greenLeavesSerial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "advance_serial")
    private int advanceSerial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fertilizer_serial")
    private int fertilizerSerial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "loan_serial")
    private int loanSerial;
    @Column(name = "branch")
    private int branch;

    public MLoginDetails() {
    }

    public MLoginDetails(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public int getLgno() {
        return lgno;
    }

    public void setLgno(int lgno) {
        this.lgno = lgno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Integer getDefaultRoute() {
        return defaultRoute;
    }

    public void setDefaultRoute(Integer defaultRoute) {
        this.defaultRoute = defaultRoute;
    }

    public Integer getDefaultVehicle() {
        return defaultVehicle;
    }

    public void setDefaultVehicle(Integer defaultVehicle) {
        this.defaultVehicle = defaultVehicle;
    }

    public int getFirewoodSerial() {
        return firewoodSerial;
    }

    public void setFirewoodSerial(int firewoodSerial) {
        this.firewoodSerial = firewoodSerial;
    }

    public int getGreenLeavesSerial() {
        return greenLeavesSerial;
    }

    public void setGreenLeavesSerial(int greenLeavesSerial) {
        this.greenLeavesSerial = greenLeavesSerial;
    }

    public int getAdvanceSerial() {
        return advanceSerial;
    }

    public void setAdvanceSerial(int advanceSerial) {
        this.advanceSerial = advanceSerial;
    }

    public int getFertilizerSerial() {
        return fertilizerSerial;
    }

    public void setFertilizerSerial(int fertilizerSerial) {
        this.fertilizerSerial = fertilizerSerial;
    }

    public int getLoanSerial() {
        return loanSerial;
    }

    public void setLoanSerial(int loanSerial) {
        this.loanSerial = loanSerial;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indexNo != null ? indexNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MLoginDetails)) {
            return false;
        }
        MLoginDetails other = (MLoginDetails) object;
        if ((this.indexNo == null && other.indexNo != null) || (this.indexNo != null && !this.indexNo.equals(other.indexNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.desktop.reports.weigh.model.MLoginDetails[ indexNo=" + indexNo + " ]";
    }

}
