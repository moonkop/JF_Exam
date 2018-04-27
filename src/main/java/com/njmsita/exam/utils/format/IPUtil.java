package com.njmsita.exam.utils.format;

import javax.servlet.http.HttpServletRequest;

public class IPUtil
{
    public static String getIP(HttpServletRequest request){
        String loginIp = request.getHeader("x-forwarded-for");
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp))
        {
            loginIp = request.getHeader("Proxy-Client-IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp))
        {
            loginIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp))
        {
            loginIp = request.getRemoteAddr();
        }
        return loginIp;
    }
}
