package com.virginia.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *Marketing activity table
 */
public class Activity implements Serializable {
    /**
     *Primary key, automatic growth, activity ID
     */
    private Integer id;

    /**
     *Activity owner ID
     */
    private Integer ownerId;

    /**
     *Activity name
     */
    private String name;

    /**
     *Activity start time
     */
    private Date startTime;

    /**
     *Activity end time
     */
    private Date endTime;

    /**
     *Activity budget, RMB
     */
    private BigDecimal costRmb;

    /**
     *Activity description
     */
    private String description;

    /**
     *Activity created time
     */
    private Date createTime;

    /**
     *Activity creator
     */
    private Integer createBy;

    /**
     *Activity edited time
     */
    private Date editTime;

    /**
     *Activity Editor
     */
    private Integer editBy;

    /**
     *Activity areas, 1 China, 2 Japan, 3 United States, 4 United Kingdom, 5 France, 6 Germany, 7 Singapore, 8 India, 9 Australia, 10 South Korea, 11 others
     */
    private Integer region;

    /**
     *Activity budget, USD
     */
    private BigDecimal costUsd;

    /**
     *Activity budget, Japanese yen
     */
    private BigDecimal costJpy;

    /**
     *Whether the activity has been deleted, 0 not deleted, 1 deleted
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getCostRmb() {
        return costRmb;
    }

    public void setCostRmb(BigDecimal costRmb) {
        this.costRmb = costRmb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public BigDecimal getCostUsd() {
        return costUsd;
    }

    public void setCostUsd(BigDecimal costUsd) {
        this.costUsd = costUsd;
    }

    public BigDecimal getCostJpy() {
        return costJpy;
    }

    public void setCostJpy(BigDecimal costJpy) {
        this.costJpy = costJpy;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", name=").append(name);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", costRmb=").append(costRmb);
        sb.append(", description=").append(description);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", editTime=").append(editTime);
        sb.append(", editBy=").append(editBy);
        sb.append(", region=").append(region);
        sb.append(", costUsd=").append(costUsd);
        sb.append(", costJpy=").append(costJpy);
        sb.append(", isDeleted=").append(isDeleted);
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
        Activity other = (Activity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
                && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
                && (this.getCostRmb() == null ? other.getCostRmb() == null : this.getCostRmb().equals(other.getCostRmb()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getEditTime() == null ? other.getEditTime() == null : this.getEditTime().equals(other.getEditTime()))
                && (this.getEditBy() == null ? other.getEditBy() == null : this.getEditBy().equals(other.getEditBy()))
                && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
                && (this.getCostUsd() == null ? other.getCostUsd() == null : this.getCostUsd().equals(other.getCostUsd()))
                && (this.getCostJpy() == null ? other.getCostJpy() == null : this.getCostJpy().equals(other.getCostJpy()))
                && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getCostRmb() == null) ? 0 : getCostRmb().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getEditTime() == null) ? 0 : getEditTime().hashCode());
        result = prime * result + ((getEditBy() == null) ? 0 : getEditBy().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getCostUsd() == null) ? 0 : getCostUsd().hashCode());
        result = prime * result + ((getCostJpy() == null) ? 0 : getCostJpy().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }
}