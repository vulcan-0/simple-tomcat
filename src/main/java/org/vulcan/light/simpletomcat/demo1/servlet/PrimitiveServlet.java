package org.vulcan.light.simpletomcat.demo1.servlet;

import org.vulcan.light.simpletomcat.demo1.Constants;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author luxiaocong
 * @createdOn 2020/11/7
 */
public class PrimitiveServlet implements Servlet {

    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("service");

        PrintWriter out = response.getWriter();
        String body = "<h1>Run PrimitiveServlet</h1>";
        String header = String.format(Constants.SUCCESS_MESSAGE_HEADER, body.length());
        out.println(header);
        out.println(body);
        out.flush();
    }

    public void destroy() {
        System.out.println("destroy");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public String getServletInfo() {
        return null;
    }

}
