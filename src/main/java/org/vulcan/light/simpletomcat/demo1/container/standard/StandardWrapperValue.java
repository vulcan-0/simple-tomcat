package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.common.HttpStatus;
import org.vulcan.light.simpletomcat.demo1.container.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardWrapperValue implements Value, Contained {

    private Container container;

    private Loader loader = new Loader();

    public void invoke(ServletRequest request, ServletResponse response, ValueContext context)
            throws IOException, ServletException {
        Class myClass = loader.load(request);
        if (myClass != null) {
            try {
                Servlet servlet = (Servlet) myClass.newInstance();
                servlet.service(request, response);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            sendNotFound(response);
        }
    }

    private void sendNotFound(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setStatus(HttpStatus.STATUS_404.getSc());
        httpResponse.setContentType(Constants.TEXT_HTML);
        httpResponse.setHeader(Constants.CONNECTION, Constants.KEEP_ALIVE);

        PrintWriter out = httpResponse.getWriter();
        String body = "<h1>File Not Found</h1>";
        out.write(body);
        httpResponse.setContentLength(body.length());
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return this.container;
    }

}
