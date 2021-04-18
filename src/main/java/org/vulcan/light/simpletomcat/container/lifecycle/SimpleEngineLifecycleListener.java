package org.vulcan.light.simpletomcat.container.lifecycle;

import org.vulcan.light.simpletomcat.common.Logger;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public class SimpleEngineLifecycleListener implements LifecycleListener {

    private Logger logger = new Logger(this.getClass());

    public void lifecycleEvent(LifecycleEvent event) {
        if (LifecycleEvent.BEFORE_START_EVENT.equals(event.getType())) {
            logger.debug("Before engine start.");
        } else if (LifecycleEvent.START_EVENT.equals(event.getType())) {
            logger.debug("Starting engine...");
        } else if (LifecycleEvent.AFTER_START_EVENT.equals(event.getType())) {
            logger.debug("After engine start.");
        } else if (LifecycleEvent.BEFORE_STOP_EVENT.equals(event.getType())) {
            logger.debug("Before engine stop.");
        } else if (LifecycleEvent.STOP_EVENT.equals(event.getType())) {
            logger.debug("Stopping engine...");
        } else if (LifecycleEvent.AFTER_STOP_EVENT.equals(event.getType())) {
            logger.debug("After engine stop.");
        } else {
            logger.debug("Unknown engine event!");
        }
    }

}
