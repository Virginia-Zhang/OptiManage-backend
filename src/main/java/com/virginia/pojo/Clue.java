package com.virginia.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
    * 线索表
    */
public class Clue implements Serializable {
    /**
    * 主键，自动增长，线索ID
    */
    private Integer id;

    /**
    * 线索所属人ID
    */
    private Integer ownerId;

    /**
    * 活动ID
    */
    private Integer activityId;

    /**
    * 姓名
    */
    private String fullName;

    /**
    * 称呼
    */
    private Integer appellation;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 微信号
    */
    private String weixin;

    /**
    * QQ号
    */
    private String qq;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 职业
    */
    private String job;

    /**
    * 年收入
    */
    private BigDecimal yearIncome;

    /**
    * 地址
    */
    private String address;

    /**
    * 是否需要贷款（0不需要，1需要）
    */
    private Integer needLoan;

    /**
    * 意向状态
    */
    private Integer intentionState;

    /**
    * 意向产品
    */
    private Integer intentionProduct;

    /**
    * 线索状态
    */
    private Integer state;

    /**
    * 线索来源
    */
    private Integer source;

    /**
    * 线索描述
    */
    private String description;

    /**
    * 下次联系时间
    */
    private Date nextContactTime;

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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public Integer getAppellation() {
        return appellation;
    }

    public void setAppellation(Integer appellation) {
        this.appellation = appellation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public BigDecimal getYearIncome() {
        return yearIncome;
    }

    public void setYearIncome(BigDecimal yearIncome) {
        this.yearIncome = yearIncome;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getNeedLoan() {
        return needLoan;
    }

    public void setNeedLoan(Integer needLoan) {
        this.needLoan = needLoan;
    }

    public Integer getIntentionState() {
        return intentionState;
    }

    public void setIntentionState(Integer intentionState) {
        this.intentionState = intentionState;
    }

    public Integer getIntentionProduct() {
        return intentionProduct;
    }

    public void setIntentionProduct(Integer intentionProduct) {
        this.intentionProduct = intentionProduct;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getNextContactTime() {
        return nextContactTime;
    }

    public void setNextContactTime(Date nextContactTime) {
        this.nextContactTime = nextContactTime;
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
        sb.append(", ownerId=").append(ownerId);
        sb.append(", activityId=").append(activityId);
        sb.append(", fullName=").append(fullName);
        sb.append(", appellation=").append(appellation);
        sb.append(", phone=").append(phone);
        sb.append(", weixin=").append(weixin);
        sb.append(", qq=").append(qq);
        sb.append(", email=").append(email);
        sb.append(", age=").append(age);
        sb.append(", job=").append(job);
        sb.append(", yearIncome=").append(yearIncome);
        sb.append(", address=").append(address);
        sb.append(", needLoan=").append(needLoan);
        sb.append(", intentionState=").append(intentionState);
        sb.append(", intentionProduct=").append(intentionProduct);
        sb.append(", state=").append(state);
        sb.append(", source=").append(source);
        sb.append(", description=").append(description);
        sb.append(", nextContactTime=").append(nextContactTime);
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
        Clue other = (Clue) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId()))
            && (this.getFullName() == null ? other.getFullName() == null : this.getFullName().equals(other.getFullName()))
            && (this.getAppellation() == null ? other.getAppellation() == null : this.getAppellation().equals(other.getAppellation()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getWeixin() == null ? other.getWeixin() == null : this.getWeixin().equals(other.getWeixin()))
            && (this.getQq() == null ? other.getQq() == null : this.getQq().equals(other.getQq()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getJob() == null ? other.getJob() == null : this.getJob().equals(other.getJob()))
            && (this.getYearIncome() == null ? other.getYearIncome() == null : this.getYearIncome().equals(other.getYearIncome()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getNeedLoan() == null ? other.getNeedLoan() == null : this.getNeedLoan().equals(other.getNeedLoan()))
            && (this.getIntentionState() == null ? other.getIntentionState() == null : this.getIntentionState().equals(other.getIntentionState()))
            && (this.getIntentionProduct() == null ? other.getIntentionProduct() == null : this.getIntentionProduct().equals(other.getIntentionProduct()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getNextContactTime() == null ? other.getNextContactTime() == null : this.getNextContactTime().equals(other.getNextContactTime()))
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
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        result = prime * result + ((getFullName() == null) ? 0 : getFullName().hashCode());
        result = prime * result + ((getAppellation() == null) ? 0 : getAppellation().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getWeixin() == null) ? 0 : getWeixin().hashCode());
        result = prime * result + ((getQq() == null) ? 0 : getQq().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getJob() == null) ? 0 : getJob().hashCode());
        result = prime * result + ((getYearIncome() == null) ? 0 : getYearIncome().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getNeedLoan() == null) ? 0 : getNeedLoan().hashCode());
        result = prime * result + ((getIntentionState() == null) ? 0 : getIntentionState().hashCode());
        result = prime * result + ((getIntentionProduct() == null) ? 0 : getIntentionProduct().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getNextContactTime() == null) ? 0 : getNextContactTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getEditTime() == null) ? 0 : getEditTime().hashCode());
        result = prime * result + ((getEditBy() == null) ? 0 : getEditBy().hashCode());
        return result;
    }
}