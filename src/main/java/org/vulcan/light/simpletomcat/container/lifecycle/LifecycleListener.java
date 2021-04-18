package org.vulcan.light.simpletomcat.container.lifecycle;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public interface LifecycleListener {

    /**
     * 监听生命周期事件
     *
     * @param event
     */
    void lifecycleEvent(LifecycleEvent event);

}
