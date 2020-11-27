package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.Container;
import org.vulcan.light.simpletomcat.demo1.container.ContainerBase;
import org.vulcan.light.simpletomcat.demo1.container.Context;
import org.vulcan.light.simpletomcat.demo1.container.Pipeline;
import org.vulcan.light.simpletomcat.demo1.lifecycle.Lifecycle;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardContext extends ContainerBase implements Context {

    public StandardContext() {
        Pipeline pipeline = new StandardPipeline();
        super.setPipeline(pipeline);
    }

    public void start() {
        StandardContextValue basic = new StandardContextValue();
        basic.setContainer(this);
        getPipeline().setBasic(basic);

        ((Lifecycle) getPipeline()).start();

        Container[] containers = findChildren();
        for (Container container : containers) {
            container.start();
        }
    }

    public void stop() {
        getPipeline().setBasic(null);
        setPipeline(null);
    }

}
