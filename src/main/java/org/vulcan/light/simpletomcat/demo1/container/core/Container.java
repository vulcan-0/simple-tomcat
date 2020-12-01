package org.vulcan.light.simpletomcat.demo1.container.core;

import org.vulcan.light.simpletomcat.demo1.container.lifecycle.Lifecycle;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/7
 */
public interface Container extends Lifecycle {

    /**
     * 设置容器名称
     *
     * @param name
     */
    void setName(String name);

    /**
     * 获取容器名称
     *
     * @return
     */
    String getName();

    /**
     * 添加子容器
     *
     * @param container
     */
    void addChild(Container container);

    /**
     * 移除子容器
     *
     * @param container
     */
    void removeChild(Container container);

    /**
     * 获取指定子容器
     *
     * @param name
     *
     * @return
     */
    Container findChild(String name);

    /**
     * 获取全部子容器
     *
     * @return
     */
    Container[] findChildren();

    /**
     * 设置父容器
     *
     * @param container
     */
    void setParent(Container container);

    /**
     * 获取父容器
     *
     * @return
     */
    Container getParent();

    /**
     * 设置管道
     *
     * @param pipeline
     */
    void setPipeline(Pipeline pipeline);

    /**
     * 获取管道
     *
     * @return
     */
    Pipeline getPipeline();

    /**
     * 处理请求
     *
     * @param request
     * @param response
     *
     * @throws IOException
     * @throws ServletException
     */
    void invoke(ServletRequest request, ServletResponse response)
            throws IOException, ServletException;

}
