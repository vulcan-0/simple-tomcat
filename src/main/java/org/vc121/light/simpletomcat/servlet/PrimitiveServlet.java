package org.vc121.light.simpletomcat.servlet;

import org.vc121.light.simpletomcat.common.Constants;
import org.vc121.light.simpletomcat.common.HttpStatus;
import org.vc121.light.simpletomcat.common.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author luxiaocong
 * @createdOn 2020/11/7
 */
public class PrimitiveServlet implements Servlet {

    private Logger logger = new Logger(this.getClass());

    public void init(ServletConfig servletConfig) throws ServletException {
        logger.debug("init");
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        logger.debug("service");

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpStatus.STATUS_201.getSc());
        httpResponse.setContentType(Constants.APPLICATION_JSON);
        httpResponse.setCharacterEncoding("utf-8");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        String body = "{\"aaa\":\"bbb\"}";
        out.println(body);

        httpResponse.setContentLength(body.length());
        out.flush();
    }

    public void destroy() {
        logger.debug("destroy");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public String getServletInfo() {
        return null;
    }

}
