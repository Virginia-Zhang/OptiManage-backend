package com.virginia.utils;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JSONUtils {
    // Convert to json data and return to the front end
    public static void print(Object data, HttpServletResponse response) throws IOException {
        String jsonString = JSON.toJSONString(data);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(jsonString);
    }
}
