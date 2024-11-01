package com.virginia.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 */
@Data
public class User implements Serializable {
    /**
     * 主键，自动增长，用户ID
     */
    private Integer id;

    /**
     * 登录账号
     */
    private String loginAct;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户手机
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 账户是否没有过期，0已过期 1正常
     */
    private Integer accountNoExpired;

    /**
     * 密码是否没有过期，0已过期 1正常
     */
    private Integer credentialsNoExpired;

    /**
     * 账号是否没有锁定，0已锁定 1正常
     */
    private Integer accountNoLocked;

    /**
     * 账号是否启用，0禁用 1启用
     */
    private Integer accountEnabled;

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

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

    /**
     * 国家地区，1 中国，2 日本，3 美国，4 英国， 5 法国，6 德国，7 新加坡，8 印度，9 澳大利亚，10 韩国，11 其他
     */
    private Integer region;

    /**
     * 偏好语言，1 英语，2 中文，3 日语
     */
    private Integer preferredLanguage;

    /**
     *Non-database fields
     */
    // Creator name
    private String createByName;
    // Editor's name
    private String editByName;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginAct=").append(loginAct);
        sb.append(", loginPwd=").append(loginPwd);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", accountNoExpired=").append(accountNoExpired);
        sb.append(", credentialsNoExpired=").append(credentialsNoExpired);
        sb.append(", accountNoLocked=").append(accountNoLocked);
        sb.append(", accountEnabled=").append(accountEnabled);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", editTime=").append(editTime);
        sb.append(", editBy=").append(editBy);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", region=").append(region);
        sb.append(", preferredLanguage=").append(preferredLanguage);
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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getLoginAct() == null ? other.getLoginAct() == null : this.getLoginAct().equals(other.getLoginAct()))
                && (this.getLoginPwd() == null ? other.getLoginPwd() == null : this.getLoginPwd().equals(other.getLoginPwd()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getAccountNoExpired() == null ? other.getAccountNoExpired() == null : this.getAccountNoExpired().equals(other.getAccountNoExpired()))
                && (this.getCredentialsNoExpired() == null ? other.getCredentialsNoExpired() == null : this.getCredentialsNoExpired().equals(other.getCredentialsNoExpired()))
                && (this.getAccountNoLocked() == null ? other.getAccountNoLocked() == null : this.getAccountNoLocked().equals(other.getAccountNoLocked()))
                && (this.getAccountEnabled() == null ? other.getAccountEnabled() == null : this.getAccountEnabled().equals(other.getAccountEnabled()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getEditTime() == null ? other.getEditTime() == null : this.getEditTime().equals(other.getEditTime()))
                && (this.getEditBy() == null ? other.getEditBy() == null : this.getEditBy().equals(other.getEditBy()))
                && (this.getLastLoginTime() == null ? other.getLastLoginTime() == null : this.getLastLoginTime().equals(other.getLastLoginTime()))
                && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
                && (this.getPreferredLanguage() == null ? other.getPreferredLanguage() == null : this.getPreferredLanguage().equals(other.getPreferredLanguage()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoginAct() == null) ? 0 : getLoginAct().hashCode());
        result = prime * result + ((getLoginPwd() == null) ? 0 : getLoginPwd().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getAccountNoExpired() == null) ? 0 : getAccountNoExpired().hashCode());
        result = prime * result + ((getCredentialsNoExpired() == null) ? 0 : getCredentialsNoExpired().hashCode());
        result = prime * result + ((getAccountNoLocked() == null) ? 0 : getAccountNoLocked().hashCode());
        result = prime * result + ((getAccountEnabled() == null) ? 0 : getAccountEnabled().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getEditTime() == null) ? 0 : getEditTime().hashCode());
        result = prime * result + ((getEditBy() == null) ? 0 : getEditBy().hashCode());
        result = prime * result + ((getLastLoginTime() == null) ? 0 : getLastLoginTime().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getPreferredLanguage() == null) ? 0 : getPreferredLanguage().hashCode());
        return result;
    }
}