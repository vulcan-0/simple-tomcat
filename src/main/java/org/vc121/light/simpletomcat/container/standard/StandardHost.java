package org.vc121.light.simpletomcat.container.standard;

import org.vc121.light.simpletomcat.container.lifecycle.LifecycleEvent;
import org.vc121.light.simpletomcat.container.core.Container;
import org.vc121.light.simpletomcat.container.core.AbstractContainerBase;
import org.vc121.light.simpletomcat.container.core.Host;
import org.vc121.light.simpletomcat.container.core.Pipeline;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardHost extends AbstractContainerBase implements Host {

    public StandardHost() {
        Pipeline pipeline = new StandardPipeline();
        super.setPipeline(pipeline);
    }

    public void start() {
        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.BEFORE_START_EVENT, "");
        StandardHostValue basic = new StandardHostValue();
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
