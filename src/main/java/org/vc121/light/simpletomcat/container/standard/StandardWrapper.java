package org.vc121.light.simpletomcat.container.standard;

import org.vc121.light.simpletomcat.container.lifecycle.LifecycleEvent;
import org.vc121.light.simpletomcat.container.core.AbstractContainerBase;
import org.vc121.light.simpletomcat.container.core.Pipeline;
import org.vc121.light.simpletomcat.container.core.Wrapper;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardWrapper extends AbstractContainerBase implements Wrapper {

    public StandardWrapper() {
        Pipeline pipeline = new StandardPipeline();
        super.setPipeline(pipeline);
    }

    public void start() {
        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.BEFORE_START_EVENT, "");
        StandardWrapperValue basic = new StandardWrapperValue();
        basic.setContainer(this);
        setBasic(basic);

        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.START_EVENT, "");
        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.AFTER_START_EVENT, "");
    }

    public void stop() {
        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.BEFORE_STOP_EVENT, "");
        setBasic(null);
        setPipeline(null);

        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.STOP_EVENT, "");
        lifecycleSupport.fireLifecycleEvent(LifecycleEvent.AFTER_STOP_EVENT, "");
    }

}
