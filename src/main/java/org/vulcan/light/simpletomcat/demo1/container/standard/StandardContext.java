package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.core.Container;
import org.vulcan.light.simpletomcat.demo1.container.core.ContainerBase;
import org.vulcan.light.simpletomcat.demo1.container.core.Context;
import org.vulcan.light.simpletomcat.demo1.container.core.Pipeline;

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
        setBasic(basic);

        Container[] containers = findChildren();
        for (Container container : containers) {
            container.start();
        }
    }

    public void stop() {
        setBasic(null);
        setPipeline(null);

        Container[] containers = findChildren();
        for (Container container : containers) {
            container.stop();
        }
    }

}
