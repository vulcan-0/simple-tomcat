package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.core.AbstractContainerBase;
import org.vulcan.light.simpletomcat.demo1.container.core.Pipeline;
import org.vulcan.light.simpletomcat.demo1.container.core.Wrapper;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.LifecycleEvent;

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
