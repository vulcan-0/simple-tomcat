package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.ContainerBase;
import org.vulcan.light.simpletomcat.demo1.container.Pipeline;
import org.vulcan.light.simpletomcat.demo1.container.Wrapper;
import org.vulcan.light.simpletomcat.demo1.lifecycle.Lifecycle;

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
        getPipeline().setBasic(basic);

        ((Lifecycle) getPipeline()).start();
    }

    public void stop() {
        getPipeline().setBasic(null);
        setPipeline(null);
    }

}
