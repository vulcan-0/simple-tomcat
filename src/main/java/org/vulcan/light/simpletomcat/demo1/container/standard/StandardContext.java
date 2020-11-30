package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.core.Container;
import org.vulcan.light.simpletomcat.demo1.container.core.AbstractContainerBase;
import org.vulcan.light.simpletomcat.demo1.container.core.Context;
import org.vulcan.light.simpletomcat.demo1.container.core.Pipeline;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.LifecycleEvent;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardContext extends AbstractContainerBase implements Context {

    public StandardContext() {
        Pipeline pipeline = new StandardPipeline();
        super.setPipeline(pipeline);
    }

    public void start() {
        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.BEFORE_START_EVENT, "");
        StandardContextValue basic = new StandardContextValue();
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
