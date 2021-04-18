package org.vulcan.light.simpletomcat.container.core;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public interface Contained {

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

}
