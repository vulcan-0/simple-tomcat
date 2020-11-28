package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.core.Container;
import org.vulcan.light.simpletomcat.demo1.container.core.ContainerBase;
import org.vulcan.light.simpletomcat.demo1.container.core.Engine;
import org.vulcan.light.simpletomcat.demo1.container.core.Pipeline;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardEngine extends ContainerBase implements Engine {

    public StandardEngine() {
        Pipeline pipeline = new StandardPipeline();
        super.setPipeline(pipeline);
    }

    public void start() {
        StandardEngineValue basic = new StandardEngineValue();
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
