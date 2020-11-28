package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.container.core.Contained;
import org.vulcan.light.simpletomcat.demo1.container.core.Container;
import org.vulcan.light.simpletomcat.demo1.container.core.Value;
import org.vulcan.light.simpletomcat.demo1.container.core.ValueContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardEngineValue implements Value, Contained {

    private Container container;

    public void invoke(ServletRequest request, ServletResponse response, ValueContext context)
            throws IOException, ServletException {
        Container host = container.findChild(Constants.DEFAULT_HOST_NAME);
        host.invoke(request, response);
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return this.container;
    }

}
