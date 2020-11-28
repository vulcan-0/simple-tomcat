package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.core.Container;
import org.vulcan.light.simpletomcat.demo1.container.core.ContainerBase;
import org.vulcan.light.simpletomcat.demo1.container.core.Host;
import org.vulcan.light.simpletomcat.demo1.container.core.Pipeline;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardHost extends ContainerBase implements Host {

    public StandardHost() {
        Pipeline pipeline = new StandardPipeline();
        super.setPipeline(pipeline);
    }

    public void start() {
        StandardHostValue basic = new StandardHostValue();
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
