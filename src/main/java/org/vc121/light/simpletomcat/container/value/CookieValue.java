package org.vc121.light.simpletomcat.container.value;

import org.vc121.light.simpletomcat.common.CookieUtils;
import org.vc121.light.simpletomcat.common.Logger;
import org.vc121.light.simpletomcat.container.core.Value;
import org.vc121.light.simpletomcat.container.core.ValueContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public class CookieValue implements Value {

    private Logger logger = new Logger(this.getClass());

    public void invoke(ServletRequest request, ServletResponse response, ValueContext context)
            throws IOException, ServletException {
        try {
            if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.addCookie(new Cookie("cookie-test", "xxx"));
                Cookie[] cookies = httpRequest.getCookies();
                logger.debug("Cookies: " + CookieUtils.getCookieString(cookies));
            }
        } finally {
            context.invokeNext(request, response);
        }
    }

}
