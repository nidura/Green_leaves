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
@Table(name = "m_suplier", catalog = "supervis_green_leaves_main", schema = "")
public class MSuplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "supno")
    private String supno;
    @Size(max = 500)
    @Column(name = "supname")
    private String supname;
    @Size(max = 255)
    @Column(name = "mobno")
    private String mobno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branch")
    private int branch;
    @Basic(optional = false)
    @NotNull
    @Column(name = "route")
    private int route;

    public MSuplier() {
    }

    public MSuplier(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public MSuplier(Integer indexNo, String supno, int branch, int route) {
        this.indexNo = indexNo;
        this.supno = supno;
        this.branch = branch;
        this.route = route;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getSupno() {
        return supno;
    }

    public void setSupno(String supno) {
        this.supno = supno;
    }

    public String getSupname() {
        return supname;
    }

    public void setSupname(String supname) {
        this.supname = supname;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
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
        if (!(object instanceof MSuplier)) {
            return false;
        }
        MSuplier other = (MSuplier) object;
        if ((this.indexNo == null && other.indexNo != null) || (this.indexNo != null && !this.indexNo.equals(other.indexNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.desktop.reports.weigh.model.MSuplier[ indexNo=" + indexNo + " ]";
    }
    
}
