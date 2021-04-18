package org.vulcan.light.simpletomcat.container.standard;

import org.vulcan.light.simpletomcat.container.core.Pipeline;
import org.vulcan.light.simpletomcat.container.core.Value;
import org.vulcan.light.simpletomcat.container.core.ValueContext;

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
public class StandardPipeline implements Pipeline {

    private ValueContext valueContext;
    private Value basic;
    private List<Value> valueList = new ArrayList<Value>();

    public StandardPipeline() {
        valueContext = new StandardValueContext();
    }

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
        ((StandardValueContext) valueContext).set(basic, getValues());
        valueContext.invokeNext(request, response);
    }

}
