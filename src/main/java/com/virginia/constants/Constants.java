package com.virginia.constants;

public class Constants {
    public static final String LOGIN_URI = "/api/login";

    //redis的key的命名规范： 项目名:模块名:功能名:唯一业务参数(比如用户id)
    public static final String REDIS_JWT_KEY = "crm:user:login:";

    //redis中负责人的key
    public static final String REDIS_OWNER_KEY = "crm:user:owner";

    //jwt过期时间7天
    public static final Long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;

    //jwt过期时间30分钟
    public static final Long DEFAULT_EXPIRE_TIME = 30 * 60 * 1000L;
}
