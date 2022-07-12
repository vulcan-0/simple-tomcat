package org.vc121.light.simpletomcat;

import org.vc121.light.simpletomcat.container.lifecycle.Lifecycle;

/**
 * @author luxiaocong
 * @createdOn 2020/12/1
 */
public interface Server extends Lifecycle {

    /**
     * 设置服务器端口
     *
     * @param port
     */
    void setPort(int port);

    /**
     * 获取服务器端口
     *
     * @return
     */
    int getPort();

    /**
     * 添加服务
     *
     * @param service
     */
    void addService(Service service);

    /**
     * 移除服务
     *
     * @param service
     */
    void removeService(Service service);

    /**
     * 获取全部服务
     *
     * @return
     */
    Service[] findServices();

}
