package com.virginia.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
    * 产品表
    */
public class Product implements Serializable {
    /**
    * 主键，自动增长，线索ID
    */
    private Integer id;

    /**
    * 产品名称
    */
    private String name;

    /**
    * 官方指导起始价
    */
    private BigDecimal guidePriceS;

    /**
    * 官方指导最高价
    */
    private BigDecimal guidePriceE;

    /**
    * 经销商报价
    */
    private BigDecimal quotation;

    /**
    * 状态 0在售 1售罄
    */
    private Integer state;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 创建人
    */
    private Integer createBy;

    /**
    * 编辑时间
    */
    private Date editTime;

    /**
    * 编辑人
    */
    private Integer editBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getGuidePriceS() {
        return guidePriceS;
    }

    public void setGuidePriceS(BigDecimal guidePriceS) {
        this.guidePriceS = guidePriceS;
    }

    public BigDecimal getGuidePriceE() {
        return guidePriceE;
    }

    public void setGuidePriceE(BigDecimal guidePriceE) {
        this.guidePriceE = guidePriceE;
    }

    public BigDecimal getQuotation() {
        return quotation;
    }

    public void setQuotation(BigDecimal quotation) {
        this.quotation = quotation;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Integer getEditBy() {
        return editBy;
    }

    public void setEditBy(Integer editBy) {
        this.editBy = editBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", guidePriceS=").append(guidePriceS);
        sb.append(", guidePriceE=").append(guidePriceE);
        sb.append(", quotation=").append(quotation);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", editTime=").append(editTime);
        sb.append(", editBy=").append(editBy);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Product other = (Product) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getGuidePriceS() == null ? other.getGuidePriceS() == null : this.getGuidePriceS().equals(other.getGuidePriceS()))
            && (this.getGuidePriceE() == null ? other.getGuidePriceE() == null : this.getGuidePriceE().equals(other.getGuidePriceE()))
            && (this.getQuotation() == null ? other.getQuotation() == null : this.getQuotation().equals(other.getQuotation()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getEditTime() == null ? other.getEditTime() == null : this.getEditTime().equals(other.getEditTime()))
            && (this.getEditBy() == null ? other.getEditBy() == null : this.getEditBy().equals(other.getEditBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGuidePriceS() == null) ? 0 : getGuidePriceS().hashCode());
        result = prime * result + ((getGuidePriceE() == null) ? 0 : getGuidePriceE().hashCode());
        result = prime * result + ((getQuotation() == null) ? 0 : getQuotation().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getEditTime() == null) ? 0 : getEditTime().hashCode());
        result = prime * result + ((getEditBy() == null) ? 0 : getEditBy().hashCode());
        return result;
    }
}