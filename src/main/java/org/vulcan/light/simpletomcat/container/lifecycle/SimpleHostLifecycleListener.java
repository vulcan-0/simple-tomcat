package org.vulcan.light.simpletomcat.container.lifecycle;

import org.vulcan.light.simpletomcat.common.Logger;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public class SimpleHostLifecycleListener implements LifecycleListener {

    private Logger logger = new Logger(this.getClass());

    public void lifecycleEvent(LifecycleEvent event) {
        if (LifecycleEvent.BEFORE_START_EVENT.equals(event.getType())) {
            logger.debug("Before host start.");
        } else if (LifecycleEvent.START_EVENT.equals(event.getType())) {
            logger.debug("Starting host...");
        } else if (LifecycleEvent.AFTER_START_EVENT.equals(event.getType())) {
            logger.debug("After host start.");
        } else if (LifecycleEvent.BEFORE_STOP_EVENT.equals(event.getType())) {
            logger.debug("Before host stop.");
        } else if (LifecycleEvent.STOP_EVENT.equals(event.getType())) {
            logger.debug("Stopping host...");
        } else if (LifecycleEvent.AFTER_STOP_EVENT.equals(event.getType())) {
            logger.debug("After host stop.");
        } else {
            logger.debug("Unknown host event!");
        }
    }

}
