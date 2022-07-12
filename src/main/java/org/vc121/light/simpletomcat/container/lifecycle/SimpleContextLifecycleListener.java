package org.vc121.light.simpletomcat.container.lifecycle;

import org.vc121.light.simpletomcat.common.Logger;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public class SimpleContextLifecycleListener implements LifecycleListener {

    private Logger logger = new Logger(this.getClass());

    public void lifecycleEvent(LifecycleEvent event) {
        if (LifecycleEvent.BEFORE_START_EVENT.equals(event.getType())) {
            logger.debug("Before context start.");
        } else if (LifecycleEvent.START_EVENT.equals(event.getType())) {
            logger.debug("Starting context...");
        } else if (LifecycleEvent.AFTER_START_EVENT.equals(event.getType())) {
            logger.debug("After context start.");
        } else if (LifecycleEvent.BEFORE_STOP_EVENT.equals(event.getType())) {
            logger.debug("Before context stop.");
        } else if (LifecycleEvent.STOP_EVENT.equals(event.getType())) {
            logger.debug("Stopping context...");
        } else if (LifecycleEvent.AFTER_STOP_EVENT.equals(event.getType())) {
            logger.debug("After context stop.");
        } else {
            logger.debug("Unknown context event!");
        }
    }

}
