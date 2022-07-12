package org.vc121.light.simpletomcat.container.standard;

import org.vc121.light.simpletomcat.container.lifecycle.LifecycleEvent;
import org.vc121.light.simpletomcat.container.core.Container;
import org.vc121.light.simpletomcat.container.core.AbstractContainerBase;
import org.vc121.light.simpletomcat.container.core.Engine;
import org.vc121.light.simpletomcat.container.core.Pipeline;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardEngine extends AbstractContainerBase implements Engine {

    public StandardEngine() {
        Pipeline pipeline = new StandardPipeline();
        super.setPipeline(pipeline);
    }

    public void start() {
        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.BEFORE_START_EVENT, "");
        StandardEngineValue basic = new StandardEngineValue();
        basic.setContainer(this);
        setBasic(basic);

        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.START_EVENT, "");
        Container[] containers = findChildren();
        for (Container container : containers) {
            container.start();
        }
        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.AFTER_START_EVENT, "");
    }

    public void stop() {
        normalStopContainer();
    }

}
