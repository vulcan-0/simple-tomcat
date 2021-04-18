package org.vulcan.light.simpletomcat.common;

import javax.servlet.http.Cookie;

/**
 * @author luxiaocong
 * @createdOn 2020/12/1
 */
public class CookieUtils {

    public static String getCookieString(Cookie cookie) {
        StringBuffer stringBuffer = new StringBuffer();
        if (cookie != null) {
            stringBuffer.append(cookie.getName()).append("=").append(cookie.getValue());
        }
        return stringBuffer.toString();
    }

    public static String getCookieString(Cookie[] cookies) {
        StringBuffer stringBuffer = new StringBuffer();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                stringBuffer.append(cookie.getName()).append("=").append(cookie.getValue()).append("; ");
            }
            stringBuffer.delete(stringBuffer.length() - 2,  stringBuffer.length());
        }
        return stringBuffer.toString();
    }

}
