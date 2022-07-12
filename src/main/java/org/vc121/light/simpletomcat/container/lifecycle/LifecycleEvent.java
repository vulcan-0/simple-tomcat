package org.vc121.light.simpletomcat.container.lifecycle;

import java.util.EventObject;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public final class LifecycleEvent extends EventObject {

    public static final String BEFORE_START_EVENT = "before_start_event";
    public static final String START_EVENT = "start_event";
    public static final String AFTER_START_EVENT = "after_start_event";
    public static final String BEFORE_STOP_EVENT = "before_stop_event";
    public static final String STOP_EVENT = "stop_event";
    public static final String AFTER_STOP_EVENT = "after_stop_event";

    private Lifecycle lifecycle;
    private String type;
    private Object data;

    public LifecycleEvent(Lifecycle lifecycle, String type) {
        this(lifecycle, type, "");
    }

    public LifecycleEvent(Lifecycle lifecycle, String type, Object data) {
        super(data);
        this.lifecycle = lifecycle;
        this.type = type;
        this.data = data;
    }

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

}
