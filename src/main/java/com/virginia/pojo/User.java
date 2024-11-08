package com.virginia.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *User table
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
    private String loginAct;

    /**
     *Login password
     */
    private String loginPwd;

    /**
     *User name
     */
    private String name;

    /**
     *User mobile phone
     */
    private String phone;

    /**
     *User email
     */
    private String email;

    /**
     *Whether the account has not expired, 0 has expired, 1 is normal
     */
    private Integer accountNoExpired;

    /**
     *Whether the password has not expired, 0 has expired, 1 is normal
     */
    private Integer credentialsNoExpired;

    /**
     *Whether the account is not locked, 0 is locked, 1 is normal
     */
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