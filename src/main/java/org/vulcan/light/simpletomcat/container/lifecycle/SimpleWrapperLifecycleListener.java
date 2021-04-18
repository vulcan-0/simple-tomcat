package org.vulcan.light.simpletomcat.container.lifecycle;

import org.vulcan.light.simpletomcat.common.Logger;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public class SimpleWrapperLifecycleListener implements LifecycleListener {

    private Logger logger = new Logger(this.getClass());

    public void lifecycleEvent(LifecycleEvent event) {
        if (LifecycleEvent.BEFORE_START_EVENT.equals(event.getType())) {
            logger.debug("Before wrapper start.");
        } else if (LifecycleEvent.START_EVENT.equals(event.getType())) {
            logger.debug("Starting wrapper...");
        } else if (LifecycleEvent.AFTER_START_EVENT.equals(event.getType())) {
            logger.debug("After wrapper start.");
        } else if (LifecycleEvent.BEFORE_STOP_EVENT.equals(event.getType())) {
            logger.debug("Before wrapper stop.");
        } else if (LifecycleEvent.STOP_EVENT.equals(event.getType())) {
            logger.debug("Stopping wrapper...");
        } else if (LifecycleEvent.AFTER_STOP_EVENT.equals(event.getType())) {
            logger.debug("After wrapper stop.");
        } else {
            logger.debug("Unknown wrapper event!");
        }
    }

}
