package com.virginia.pojo;

import java.io.Serializable;

/**
    *Dictionary value table
    */
public class DicValue implements Serializable {
    /**
    *Primary key, automatic growth, dictionary value ID
    */
    private Integer id;

    /**
    *Dictionary type code
    */
    private String typeCode;

    /**
    *Dictionary value
    */
    private String typeValue;

    /**
    *Dictionary value sorting
    */
    private Integer order;

    /**
    *Remark
    */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue == null ? null : typeValue.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", typeValue=").append(typeValue);
        sb.append(", order=").append(order);
        sb.append(", remark=").append(remark);
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
        DicValue other = (DicValue) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTypeCode() == null ? other.getTypeCode() == null : this.getTypeCode().equals(other.getTypeCode()))
            && (this.getTypeValue() == null ? other.getTypeValue() == null : this.getTypeValue().equals(other.getTypeValue()))
            && (this.getOrder() == null ? other.getOrder() == null : this.getOrder().equals(other.getOrder()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTypeCode() == null) ? 0 : getTypeCode().hashCode());
        result = prime * result + ((getTypeValue() == null) ? 0 : getTypeValue().hashCode());
        result = prime * result + ((getOrder() == null) ? 0 : getOrder().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}