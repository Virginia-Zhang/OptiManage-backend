package com.virginia.pojo;

import java.io.Serializable;
import java.util.Date;

/**
    *Transaction tracking record sheet
    */
public class TranRemark implements Serializable {
    /**
    *Primary key, automatic growth, transaction note ID
    */
    private Integer id;

    /**
    *Transaction ID
    */
    private Integer tranId;

    /**
    *Tracking method
    */
    private Integer noteWay;

    /**
    *Track content
    */
    private String noteContent;

    /**
    *Track time
    */
    private Date createTime;

    /**
    *Stalking people
    */
    private Integer createBy;

    /**
    *Editing time
    */
    private Date editTime;

    /**
    *Editor
    */
    private Integer editBy;

    /**
    *Deletion status (0 normal, 1 deleted)
    */
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTranId() {
        return tranId;
    }

    public void setTranId(Integer tranId) {
        this.tranId = tranId;
    }

    public Integer getNoteWay() {
        return noteWay;
    }

    public void setNoteWay(Integer noteWay) {
        this.noteWay = noteWay;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent == null ? null : noteContent.trim();
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tranId=").append(tranId);
        sb.append(", noteWay=").append(noteWay);
        sb.append(", noteContent=").append(noteContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", editTime=").append(editTime);
        sb.append(", editBy=").append(editBy);
        sb.append(", deleted=").append(deleted);
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
        TranRemark other = (TranRemark) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTranId() == null ? other.getTranId() == null : this.getTranId().equals(other.getTranId()))
            && (this.getNoteWay() == null ? other.getNoteWay() == null : this.getNoteWay().equals(other.getNoteWay()))
            && (this.getNoteContent() == null ? other.getNoteContent() == null : this.getNoteContent().equals(other.getNoteContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getEditTime() == null ? other.getEditTime() == null : this.getEditTime().equals(other.getEditTime()))
            && (this.getEditBy() == null ? other.getEditBy() == null : this.getEditBy().equals(other.getEditBy()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTranId() == null) ? 0 : getTranId().hashCode());
        result = prime * result + ((getNoteWay() == null) ? 0 : getNoteWay().hashCode());
        result = prime * result + ((getNoteContent() == null) ? 0 : getNoteContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getEditTime() == null) ? 0 : getEditTime().hashCode());
        result = prime * result + ((getEditBy() == null) ? 0 : getEditBy().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }
}