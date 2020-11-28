package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.core.ContainerBase;
import org.vulcan.light.simpletomcat.demo1.container.core.Pipeline;
import org.vulcan.light.simpletomcat.demo1.container.core.Wrapper;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardWrapper extends ContainerBase implements Wrapper {

    public StandardWrapper() {
        Pipeline pipeline = new StandardPipeline();
        super.setPipeline(pipeline);
    }

    public void start() {
        StandardWrapperValue basic = new StandardWrapperValue();
        basic.setContainer(this);
        setBasic(basic);
    }

    public void stop() {
        setBasic(null);
        setPipeline(null);
    }

}
