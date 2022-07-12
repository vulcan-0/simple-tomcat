package org.vc121.light.simpletomcat.container.value;

import org.vc121.light.simpletomcat.common.Constants;
import org.vc121.light.simpletomcat.common.HttpStatus;
import org.vc121.light.simpletomcat.container.core.Value;
import org.vc121.light.simpletomcat.container.core.ValueContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/28
 */
public class ServerInternalErrorValue implements Value {

    public void invoke(ServletRequest request, ServletResponse response, ValueContext context)
            throws IOException, ServletException {
        try {
            context.invokeNext(request, response);
        } catch (IOException e) {
            responseError(response, e);
            throw e;
        } catch (ServletException e) {
            responseError(response, e);
            throw e;
        }
    }

    private void responseError(ServletResponse response, Exception e) throws IOException {
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpStatus.STATUS_500.getSc());
            httpResponse.setContentType(Constants.TEXT_HTML);
            httpResponse.setHeader(Constants.CONNECTION, Constants.KEEP_ALIVE);
            String message = String.format(Constants.ERROR_MESSAGE_500, e.getLocalizedMessage());
            response.getWriter().write(message);
            httpResponse.setContentLengthLong(message.length());
        }
    }

}
