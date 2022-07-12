package org.vc121.light.simpletomcat.container.lifecycle;

/**
 * @author luxiaocong
 * @createdOn 2020/11/7
 */
public interface Lifecycle {

    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();

    /**
     * 添加生命周期事件监听器
     *
     * @param lifecycleListener
     */
    void addLifecycleListener(LifecycleListener lifecycleListener);

    /**
     * 移除生命周期事件监听器
     *
     * @param lifecycleListener
     */
    void removeLifecycleListener(LifecycleListener lifecycleListener);

}
