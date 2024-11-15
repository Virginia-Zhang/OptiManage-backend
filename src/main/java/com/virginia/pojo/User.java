package com.virginia.pojo;

import com.virginia.validation.ValidationGroups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User entity
 */
@Data
public class User implements Serializable {
    /**
     *Primary key, automatic growth, user ID
     */
    private Integer id;

    /**
     *Login account
     */
    @NotEmpty(groups = {ValidationGroups.AddUserGroup.class, ValidationGroups.EditUserGroup.class}, message = "Login account is required!")
    private String loginAct;

    /**
     *Login password
     */
    private String loginPwd;

    /**
     *User name
     */
    @NotEmpty(groups = {ValidationGroups.AddUserGroup.class, ValidationGroups.EditUserGroup.class}, message = "Name is required!")
    private String name;

    /**
     *User mobile phone
     */
    @NotEmpty(groups = {ValidationGroups.AddUserGroup.class, ValidationGroups.EditUserGroup.class}, message = "Phone number is required!")
    private String phone;

    /**
     *User email
     */
    @NotEmpty(groups = {ValidationGroups.AddUserGroup.class, ValidationGroups.EditUserGroup.class}, message = "Email is required!")
    @Email(groups = {ValidationGroups.AddUserGroup.class, ValidationGroups.EditUserGroup.class}, message = "Invalid email format!")
    private String email;

    /**
     *Whether the account has not expired, 0 has expired, 1 is normal
     */
    @NotNull(groups = {ValidationGroups.EditUserGroup.class}, message = "Account expired status is required!")
    private Integer accountNoExpired;

    /**
     *Whether the password has not expired, 0 has expired, 1 is normal
     */
    @NotNull(groups = {ValidationGroups.EditUserGroup.class}, message = "Credentials expired status is required!")
    private Integer credentialsNoExpired;

    /**
     *Whether the account is not locked, 0 is locked, 1 is normal
     */
    @NotNull(groups = {ValidationGroups.EditUserGroup.class}, message = "Account locked status is required!")
    private Integer accountNoLocked;

    /**
     *Whether the account is enabled, 0 is disabled, 1 is enabled
     */
    private Integer accountEnabled;

    /**
     *Creation time
     */
    private LocalDateTime createTime;

    /**
     *Creator
     */
    private Integer createBy;

    /**
     *Editing time
     */
    private LocalDateTime editTime;

    /**
     *Editor
     */
    private Integer editBy;

    /**
     *Last login time
     */
    private LocalDateTime lastLoginTime;

    /**
     *Country and region, 1 China, 2 Japan, 3 United States, 4 United Kingdom, 5 France, 6 Germany, 7 Singapore, 8 India, 9 Australia, 10 South Korea, 11 others
     */
    @NotNull(groups = {ValidationGroups.AddUserGroup.class, ValidationGroups.EditUserGroup.class}, message = "Region is required!")
    private Integer region;

    /**
     *Preferred language, 1 English, 2 Chinese, 3 Japanese
     */
    private Integer preferredLanguage;

    /**
     *Non-database fields
     */
    // Creator's login_act
    private String createByAct;
    // Editor's login_act
    private String editByAct;

    private static final long serialVersionUID = 1L;
}