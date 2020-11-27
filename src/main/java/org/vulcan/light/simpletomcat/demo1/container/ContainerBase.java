package org.vulcan.light.simpletomcat.demo1.container;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public abstract class ContainerBase implements Container, Pipeline {

    private String name;
    private Map<String, Container> containerMap = new HashMap<String, Container>();
    private List<Container> containerList = new ArrayList<Container>();
    private Container parent;
    private Pipeline pipeline;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addChild(Container container) {
        containerMap.put(container.getName(), container);
        containerList.add(container);
        container.setParent(this);
    }

    public void removeChild(Container container) {
        containerMap.remove(container);
        containerList.remove(container);
        container.setParent(null);
    }

    public Container findChild(String name) {
        return containerMap.get(name);
    }

    public Container[] findChildren() {
        return containerList.toArray(new Container[containerList.size()]);
    }

    public void setParent(Container container) {
        this.parent = container;
    }

    public Container getParent() {
        return this.parent;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void invoke(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        pipeline.invoke(request, response);
    }

    public void setBasic(Value value) {
        getPipeline().setBasic(value);
    }

    public Value getBasic() {
        return getPipeline().getBasic();
    }

    public void addValue(Value value) {
        getPipeline().addValue(value);
    }

    public void removeValue(Value value) {
        getPipeline().removeValue(value);
    }

    public Value[] getValues() {
        return new Value[0];
    }

}
