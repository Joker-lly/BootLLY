package com.lly.util.user;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    public static UserSession getUserSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("userSession") != null)
            return (UserSession) request.getSession().getAttribute("userSession");
        else
            return null;
    }
}
