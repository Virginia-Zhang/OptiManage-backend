package com.virginia.constants;

public class Constants {
    public static final String LOGIN_URI = "/api/login";

    //The naming convention of redis key: project name: module name: function name: unique business parameter (such as user id)
    public static final String REDIS_JWT_KEY = "crm:user:login:";

    //The redis key of the owner
    public static final String REDIS_OWNER_KEY = "crm:user:owner";

    //Jwt expiration time: 7 days
//    public static final Long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;
    public static final Long EXPIRE_TIME = 8 * 60 * 1000L;

    //Default Jwt expiration time: 30 minutes
//    public static final Long DEFAULT_EXPIRE_TIME = 30 * 60 * 1000L;
    public static final Long DEFAULT_EXPIRE_TIME = 5 * 60 * 1000L;

    // Secret key for JWT
    public static final String SECRET_KEY = "dY8300olWQ33451d<3w48du7890-lghy678_6ad5-(!a15hg";
}
