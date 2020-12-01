package org.vulcan.light.simpletomcat.demo1.container.value;

import org.vulcan.light.simpletomcat.demo1.common.CookieUtils;
import org.vulcan.light.simpletomcat.demo1.common.Logger;
import org.vulcan.light.simpletomcat.demo1.container.core.Value;
import org.vulcan.light.simpletomcat.demo1.container.core.ValueContext;

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
