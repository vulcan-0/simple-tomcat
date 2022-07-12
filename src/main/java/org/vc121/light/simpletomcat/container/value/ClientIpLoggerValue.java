package org.vc121.light.simpletomcat.container.value;

import org.vc121.light.simpletomcat.common.Logger;
import org.vc121.light.simpletomcat.container.core.Value;
import org.vc121.light.simpletomcat.container.core.ValueContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/28
 */
public class ClientIpLoggerValue implements Value {

    private Logger logger = new Logger(this.getClass());

    public void invoke(ServletRequest request, ServletResponse response, ValueContext context)
            throws IOException, ServletException {
        logger.debug("RemoteAddress[" + request.getRemoteAddr() + "]");
        context.invokeNext(request, response);
    }

}
