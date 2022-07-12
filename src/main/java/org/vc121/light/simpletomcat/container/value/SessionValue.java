package org.vc121.light.simpletomcat.container.value;

import org.vc121.light.simpletomcat.common.Constants;
import org.vc121.light.simpletomcat.common.Logger;
import org.vc121.light.simpletomcat.container.core.Value;
import org.vc121.light.simpletomcat.container.core.ValueContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public class SessionValue implements Value {

    private Logger logger = new Logger(this.getClass());

    public void invoke(ServletRequest request, ServletResponse response, ValueContext context)
            throws IOException, ServletException {
        try {
            if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                HttpSession session = httpRequest.getSession();
                if (!httpRequest.isRequestedSessionIdFromURL() && !httpRequest.isRequestedSessionIdFromCookie()) {
                    Cookie cookie = new Cookie(Constants.JSESSIONID, session.getId());
                    httpResponse.addCookie(cookie);
                }
                Integer count = (Integer) session.getAttribute("count");
                if (count == null) {
                    count = 0;
                } else {
                    count++;
                }
                session.setAttribute("count", count);
                logger.debug("SessionId: " + session.getId() + ", count: " + count);
            }
        } finally {
            context.invokeNext(request, response);
        }
    }

}
