/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.desktop.reports.weigh.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Nidura Prageeth
 */
@Entity(name = "com.sv.desktop.reports.weigh.model.TGreenLeavesWeigh")
@Table(name = "t_green_leaves_weigh", catalog = "supervis_green_leaves_main", schema = "")
public class TGreenLeavesWeigh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "boiled_leaves")
    private BigDecimal boiledLeaves;
    @Column(name = "coarse_leaves")
    private BigDecimal coarseLeaves;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "general_deduction")
    private BigDecimal generalDeduction;
    @Column(name = "general_deduction_percent")
    private BigDecimal generalDeductionPercent;
    @Size(max = 255)
    @Column(name = "green_leaves_type")
    private String greenLeavesType;
    @Column(name = "net_weight")
    private BigDecimal netWeight;
    @Size(max = 255)
    @Column(name = "serial")
    private String serial;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @Column(name = "tare_calculated")
    private BigDecimal tareCalculated;
    @Column(name = "tare_deduction")
    private BigDecimal tareDeduction;
    @Size(max = 255)
    @Column(name = "temp_supplier_remark")
    private String tempSupplierRemark;
    @Column(name = "total_bag_count")
    private Integer totalBagCount;
    @Column(name = "total_weight")
    private BigDecimal totalWeight;
    @Column(name = "water_deduction")
    private BigDecimal waterDeduction;
    @JoinColumn(name = "supplier", referencedColumnName = "index_no")
    @ManyToOne(fetch = FetchType.LAZY)
    private MSuplier supplier;

    public TGreenLeavesWeigh() {
    }

    public TGreenLeavesWeigh(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public BigDecimal getBoiledLeaves() {
        return boiledLeaves;
    }

    public void setBoiledLeaves(BigDecimal boiledLeaves) {
        this.boiledLeaves = boiledLeaves;
    }

    public BigDecimal getCoarseLeaves() {
        return coarseLeaves;
    }

    public void setCoarseLeaves(BigDecimal coarseLeaves) {
        this.coarseLeaves = coarseLeaves;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getGeneralDeduction() {
        return generalDeduction;
    }

    public void setGeneralDeduction(BigDecimal generalDeduction) {
        this.generalDeduction = generalDeduction;
    }

    public BigDecimal getGeneralDeductionPercent() {
        return generalDeductionPercent;
    }

    public void setGeneralDeductionPercent(BigDecimal generalDeductionPercent) {
        this.generalDeductionPercent = generalDeductionPercent;
    }

    public String getGreenLeavesType() {
        return greenLeavesType;
    }

    public void setGreenLeavesType(String greenLeavesType) {
        this.greenLeavesType = greenLeavesType;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTareCalculated() {
        return tareCalculated;
    }

    public void setTareCalculated(BigDecimal tareCalculated) {
        this.tareCalculated = tareCalculated;
    }

    public BigDecimal getTareDeduction() {
        return tareDeduction;
    }

    public void setTareDeduction(BigDecimal tareDeduction) {
        this.tareDeduction = tareDeduction;
    }

    public String getTempSupplierRemark() {
        return tempSupplierRemark;
    }

    public void setTempSupplierRemark(String tempSupplierRemark) {
        this.tempSupplierRemark = tempSupplierRemark;
    }

    public Integer getTotalBagCount() {
        return totalBagCount;
    }

    public void setTotalBagCount(Integer totalBagCount) {
        this.totalBagCount = totalBagCount;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getWaterDeduction() {
        return waterDeduction;
    }

    public void setWaterDeduction(BigDecimal waterDeduction) {
        this.waterDeduction = waterDeduction;
    }

    public MSuplier getSupplier() {
        return supplier;
    }

    public void setSupplier(MSuplier supplier) {
        this.supplier = supplier;
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
        if (!(object instanceof TGreenLeavesWeigh)) {
            return false;
        }
        TGreenLeavesWeigh other = (TGreenLeavesWeigh) object;
        if ((this.indexNo == null && other.indexNo != null) || (this.indexNo != null && !this.indexNo.equals(other.indexNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.desktop.reports.weigh.model.TGreenLeavesWeigh[ indexNo=" + indexNo + " ]";
    }
    
}
