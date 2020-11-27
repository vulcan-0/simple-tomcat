package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.Contained;
import org.vulcan.light.simpletomcat.demo1.container.Container;
import org.vulcan.light.simpletomcat.demo1.container.Value;
import org.vulcan.light.simpletomcat.demo1.container.ValueContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardHostValue implements Value, Contained {

    private Container container;

    public void invoke(ServletRequest request, ServletResponse response, ValueContext context)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String contextPath = httpRequest.getContextPath();
        Container contextContainer = container.findChild(contextPath);
        if (contextContainer != null) {
            contextContainer.invoke(request, response);
        } else {
            throw new ServletException("Not found Context!");
        }
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return this.container;
    }

}
