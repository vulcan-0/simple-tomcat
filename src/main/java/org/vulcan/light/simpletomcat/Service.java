package org.vulcan.light.simpletomcat;

import org.vulcan.light.simpletomcat.connector.Connector;
import org.vulcan.light.simpletomcat.container.core.Container;
import org.vulcan.light.simpletomcat.container.lifecycle.Lifecycle;

/**
 * @author luxiaocong
 * @createdOn 2020/12/1
 */
public interface Service extends Lifecycle {

    /**
     * 设置服务器
     *
     * @param server
     */
    void setServer(Server server);

    /**
     * 获取服务器
     *
     * @return
     */
    Server getServer();

    /**
     * 设置容器
     *
     * @param container
     */
    void setContainer(Container container);

    /**
     * 获取容器
     *
     * @return
     */
    Container getContainer();

    /**
     * 添加连接器
     *
     * @param connector
     */
    void addConnector(Connector connector);

    /**
     * 移除连接器
     *
     * @param connector
     */
    void removeConnector(Connector connector);

    /**
     * 获取全部连接器
     *
     * @return
     */
    Connector[] findConnectors();

}
