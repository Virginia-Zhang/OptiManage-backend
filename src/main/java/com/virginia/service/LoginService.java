package com.virginia.service;

import com.virginia.query.LoginQuery;
import com.virginia.result.R;

public interface LoginService {
    R checkLogin(LoginQuery loginQuery);
}
