package com.virginia.utils;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JSONUtils {
    // 转换成JSON数据返回给前端
    public static void print(Object data, HttpServletResponse response) throws IOException {
        // 设置为JSON数据格式并返回给前端
        String jsonString = JSON.toJSONString(data);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(jsonString);
    }
}
