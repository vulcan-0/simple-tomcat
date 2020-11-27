package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.Value;
import org.vulcan.light.simpletomcat.demo1.container.ValueContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardValueContext implements ValueContext {

    private Value basic;
    private Value[] values;
    private int stage;

    public void invokeNext(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {

        if (stage < values.length) {
            values[stage].invoke(request, response, this);
        } else if (stage == values.length && basic != null) {
            basic.invoke(request, response, this);
        } else {
            throw new ServletException("There's no value!");
        }

        stage++;
    }

    void set(Value basic, Value[] values) {
        this.basic = basic;
        this.values = values;
        stage = 0;
    }

}
