package org.vulcan.light.simpletomcat.demo1.container.standard;

import org.vulcan.light.simpletomcat.demo1.container.core.Container;
import org.vulcan.light.simpletomcat.demo1.container.core.AbstractContainerBase;
import org.vulcan.light.simpletomcat.demo1.container.core.Context;
import org.vulcan.light.simpletomcat.demo1.container.core.Pipeline;
import org.vulcan.light.simpletomcat.demo1.container.filter.ApplicationFilterConfig;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.LifecycleEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public class StandardContext extends AbstractContainerBase implements Context {

    private List<ApplicationFilterConfig> filterConfigs = new ArrayList<ApplicationFilterConfig>();

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

    public void addFilterConfig(ApplicationFilterConfig filterConfig) {
        filterConfigs.add(filterConfig);
    }

    public void removeFilterConfig(ApplicationFilterConfig filterConfig) {
        filterConfigs.remove(filterConfig);
    }

    public List<ApplicationFilterConfig> findFilterConfigs() {
        return filterConfigs;
    }

}
