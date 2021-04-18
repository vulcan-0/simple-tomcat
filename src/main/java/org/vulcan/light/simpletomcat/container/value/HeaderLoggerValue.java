package org.vulcan.light.simpletomcat.container.value;

import org.vulcan.light.simpletomcat.common.Logger;
import org.vulcan.light.simpletomcat.container.core.Value;
import org.vulcan.light.simpletomcat.container.core.ValueContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

/**
 * @author luxiaocong
 * @createdOn 2020/11/28
 */
public class HeaderLoggerValue implements Value {

    private Logger logger = new Logger(this.getClass());

    public void invoke(ServletRequest request, ServletResponse response, ValueContext context)
            throws IOException, ServletException {
        logRequestHeader(request);
        try {
            context.invokeNext(request, response);
        } finally {
            logResponseHeader(response);
        }
    }

    private void logRequestHeader(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            Enumeration<String> names = httpRequest.getHeaderNames();
            StringBuffer buffer = new StringBuffer();
            if (names.hasMoreElements()) {
                buffer.append("Request Headers =>\n");
                while (names.hasMoreElements()) {
                    String name = names.nextElement();
                    buffer.append(name).append(": ");
                    Enumeration<String> values = httpRequest.getHeaders(name);
                    if (values != null && values.hasMoreElements()) {
                        while (values.hasMoreElements()) {
                            String value = values.nextElement();
                            buffer.append(value).append(", ");
                        }
                        buffer.delete(buffer.length() - 2, buffer.length());
                        buffer.append("\n");
                    }
                }
                buffer.delete(buffer.length() - 1, buffer.length());
            }
            logger.debug(buffer.toString());
        }
    }

    private void logResponseHeader(ServletResponse response) {
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            Collection<String> names = httpResponse.getHeaderNames();
            StringBuffer buffer = new StringBuffer();
            if (!names.isEmpty()) {
                buffer.append("Response Headers =>\n");
                for (String name : names) {
                    buffer.append(name).append(": ");
                    Collection<String> values = httpResponse.getHeaders(name);
                    if (values != null && !values.isEmpty()) {
                        for (String value : values) {
                            buffer.append(value).append(", ");
                        }
                        buffer.delete(buffer.length() - 2, buffer.length());
                        buffer.append("\n");
                    }
                }
                buffer.delete(buffer.length() - 1, buffer.length());
            }
            logger.debug(buffer.toString());
        }
    }

}
