package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.Pipeline;
import org.vulcan.light.simpletomcat.demo1.container.Value;
import org.vulcan.light.simpletomcat.demo1.container.ValueContext;
import org.vulcan.light.simpletomcat.demo1.lifecycle.Lifecycle;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardPipeline implements Pipeline, Lifecycle {

    private ValueContext valueContext;
    private Value basic;
    private List<Value> valueList = new ArrayList<Value>();

    public void setBasic(Value value) {
        this.basic = value;
    }

    public Value getBasic() {
        return this.basic;
    }

    public void addValue(Value value) {
        valueList.add(value);
    }

    public void removeValue(Value value) {
        valueList.remove(value);
    }

    public Value[] getValues() {
        return valueList.toArray(new Value[valueList.size()]);
    }

    public void invoke(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        valueContext.invokeNext(request, response);
    }

    public void start() {
        valueContext = new StandardValueContext();
        ((StandardValueContext) valueContext).set(basic, getValues());
    }

    public void stop() {
        valueContext = null;
        basic = null;
        valueList = null;
    }

}
