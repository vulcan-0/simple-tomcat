package org.vc121.light.simpletomcat.container.lifecycle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public final class LifecycleSupport {

    private Lifecycle lifecycle;
    private List<LifecycleListener> listeners = new ArrayList<LifecycleListener>();

    public LifecycleSupport(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public void addLifecycleListener(LifecycleListener lifecycleListener) {
        listeners.add(lifecycleListener);
    }

    public void removeLifecycleListener(LifecycleListener lifecycleListener) {
        listeners.remove(lifecycleListener);
    }

    public void fireLifecycleEvent(String type, Object data) {
        LifecycleEvent lifecycleEvent = new LifecycleEvent(lifecycle, type, data);
        List<LifecycleListener> currentListeners;
        synchronized (listeners) {
            currentListeners = new ArrayList<LifecycleListener>(listeners);
        }
        for (LifecycleListener lifecycleListener : currentListeners) {
            lifecycleListener.lifecycleEvent(lifecycleEvent);
        }
    }

}
